package com.xaskysad.xmlparser_master.xmlparser_master;

import android.support.v4.util.Pair;
import android.util.Xml;


import com.xaskysad.xmlparser_master.structalgo.PairStack;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import com.gc.reflectionutil.IUtil;
import com.gc.reflectionutil.ReflectionUtil;


public class XmlParserUtil3 {

    static IUtil iUtil = new ReflectionUtil();

    public static <T> T parser(InputStream input, String encoding, Class<T> clazz) throws Exception {


        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(input, encoding);



        PairStack<String,Object> pairStack = new PairStack<>();

        T t = null;


        int eventType = parser.getEventType();//start document
        while (eventType != XmlPullParser.END_DOCUMENT) {

            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:

                    t = clazz.newInstance();

                    pairStack.put(new Pair<String, Object>(t.getClass().getName(),t));


                    break;

                case XmlPullParser.START_TAG:

                    String parserName = parser.getName();

                    Object topObj =pairStack.peek().second;

                    Field controlField = getControlField(topObj, parserName);
                    if (controlField == null)
                        break;

                    Object fieldObject = controlField.getType().newInstance();

                    if (controlField.getType().isPrimitive() || controlField.getType() == String.class) {
                        String atText = parser.nextText();

                        controlField.set(topObj, atText);


                    } else {

                        if (controlField.getAnnotation(UserNode.class) != null) {

                            Field[] gFields = controlField.getType().getFields();
                            for (Field field1 : gFields) {

                                handleElementNode(parser,fieldObject,field1);

                            }
                            controlField.set(topObj, fieldObject);


                            pairStack.put(new Pair<String, Object>(controlField.getAnnotation(UserNode.class).value(),fieldObject));
                        }
                        else if (controlField.getAnnotation(TList.class) != null) {


                            Object listObj = controlField.get(topObj);
                            if (listObj == null) {
                                listObj = controlField.getType().newInstance();
                                controlField.set(topObj, listObj);
                            }


                            Class genericClazz = iUtil.getGenericClazz(controlField);
                            Object object = genericClazz.newInstance();

                            Method method = List.class.getMethod("add", Object.class);
                            method.invoke(listObj, object);

                            Field[] gFields = object.getClass().getFields();
                            for (Field field1 : gFields) {

                                handleElementNode(parser,object,field1);

                            }
                            pairStack.put(new Pair<String, Object>(controlField.getAnnotation(TList.class).value(),object));

                        }

                    }


                    break;
                case XmlPullParser.END_TAG:

                    String parserName2 = parser.getName();

                    boolean flag = false;

                    do {

                        flag = pairStack.popIfFrist(parserName2);


                    }while( flag &&!pairStack.isEmpty());



                    break;
            }

            eventType = parser.next();

        }

        return t;

    }


    private static void handleElementNode(XmlPullParser parser,Object object, Field field) throws IllegalAccessException {
        NamespaceNode namespaceNode = field.getAnnotation(NamespaceNode.class);
        if (namespaceNode != null) {

            String prefix = namespaceNode.prefix();

            String namespace = parser.getNamespace();

            if (!"".equals(prefix)) {
                namespace = parser.getNamespace(prefix);
            }

            field.set(object, namespace);

            return;
        }

        AttributeNode attributeNode = field.getAnnotation(AttributeNode.class);
        if (attributeNode != null) {

            int index = attributeNode.index();

            String s = parser.getAttributeValue(index);

            field.set(object, s);

            return;
        }
    }

    private static Field getControlField(Object topObj, String parserName) {


        List<Field> fields = iUtil.getDeclaredFieldsByAnnotation(topObj.getClass(), TextNode.class);
        for (Field field : fields) {
            String atText = field.getAnnotation(TextNode.class).value();
            if (atText.equals(parserName)) {
                return field;
            }
        }

        fields = iUtil.getDeclaredFieldsByAnnotation(topObj.getClass(), UserNode.class);
        for (Field field : fields) {
            String atText = field.getAnnotation(UserNode.class).value();
            if (atText.equals(parserName)) {
                return field;
            }
        }
        fields = iUtil.getDeclaredFieldsByAnnotation(topObj.getClass(), TList.class);

        for (Field field : fields) {

            Class genericClazz = iUtil.getGenericClazz(field);
            if (genericClazz != null) {
                String atText = field.getAnnotation(TList.class).value();
                if (atText.equals(parserName)) {
                    return field;
                }
            }

        }

        return null;

    }


}
