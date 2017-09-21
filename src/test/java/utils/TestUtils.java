package utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.assertj.core.api.Assertions;

public class TestUtils {
    public static Object callMethod(Class classe, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = classe.getMethod(methodName);
        method.setAccessible(true);
        return method.invoke(null);
    }

    public static Object callMethod(Class classe, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = classe.getMethod(methodName);
        method.setAccessible(true);
        return method.invoke(null, parameters);
    }

    public static Object callMethod(Object o, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = o.getClass().getMethod(methodName);
        method.setAccessible(true);
        Object resultat = method.invoke(o);
        return resultat;
    }

    public static Class toPrimitiveType(Class type){
        if(type == Integer.class) {
            return int.class;
        }
        else if(type == Double.class) {
            return double.class;
        }
        else if(type == Character.class) {
            return char.class;
        }
        else if(type == Byte.class) {
            return byte.class;
        }
        else if(type == Boolean.class) {
            return boolean.class;
        }
        else if(type == Float.class) {
            return float.class;
        }
        else if(type == Long.class) {
            return long.class;
        }
        else {
            return short.class;
        }
    }

    public static Object callMethod(Object o, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] params = parameters.clone();
        Class[] classes = new Class[parameters.length];

        for(int i = 0; i < parameters.length; i++){
            classes[i] = params[i].getClass();
        }
        Method method = o.getClass().getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        Object resultat = method.invoke(o, parameters);
        return resultat;
    }

    public static Object callMethodPrimitiveParameters(Object o, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] params = parameters.clone();
        Class[] classes = new Class[parameters.length];

        for(int i = 0; i < parameters.length; i++){
            classes[i] = toPrimitiveType(params[i].getClass());
        }
        Method method = o.getClass().getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        Object resultat = method.invoke(o, parameters);
        return resultat;
    }

    public static void checkStaticMethod(Class classe, String nomMethode, Class returnType, int nbParameters) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode);
            Assertions.assertThat(method.getParameterCount()).isEqualTo(nbParameters);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée");
        }
        Assertions.assertThat(Modifier.isPublic(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas publique").isTrue();
        Assertions.assertThat(Modifier.isStatic(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas statique").isTrue();

    }

    public static void checkMethod(Class classe, String nomMethode, Class returnType, Class... parameters) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode, parameters);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée");
        }
        Assertions.assertThat(Modifier.isPublic(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas publique").isTrue();

    }

    public static void checkPrivateMethod(Class classe, String nomMethode, Class returnType, Class... parameters) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode, parameters);
            method.setAccessible(true);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée");
        }
        Assertions.assertThat(Modifier.isPrivate(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas privée").isTrue();

    }

    public static void checkAbstractMethod(Class classe, String nomMethode, Class returnType, Class... parameters) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode, parameters);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée");
        }
        Assertions.assertThat(Modifier.isPublic(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas publique").isTrue();
        Assertions.assertThat(Modifier.isAbstract(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas publique").isTrue();

    }

    public static void checkConstructor(Class classe, Class... parameters) {
        Constructor constructor = null;
        try {
            constructor = classe.getConstructor(parameters);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Le constructeur pour la classe " + classe.getName() + " n'existe pas");
        }
        Assertions.assertThat(Modifier.isPublic(constructor.getModifiers())).as("Le constructeur de la classe " + classe.getName() + " n'est pas public").isTrue();

    }

    public static void checkStaticFinalField(Class classe, String nomChamp, Class type, Object valeur) throws IllegalAccessException {
        Field field = null;
        try {
            field = classe.getField(nomChamp);
            Assertions.assertThat(field.getType()).isEqualTo(type);
            Assertions.assertThat(field.get(null)).as("Le champ " + nomChamp + " n'a pas la valeur attendue").isEqualTo(valeur);
        } catch (NoSuchFieldException exception) {
            Assertions.fail("Aucun champ nommé " + nomChamp + " n'a été trouvé");
        }
        Assertions.assertThat(Modifier.isFinal(field.getModifiers())).as("Le champ " + nomChamp + " n'est pas une constante").isTrue();
        Assertions.assertThat(Modifier.isStatic(field.getModifiers())).as("Le champ " + nomChamp + " n'est pas une constante de classe").isTrue();
        Assertions.assertThat(Modifier.isPublic(field.getModifiers())).as("Le champ " + nomChamp + " n'est pas accessible").isTrue();
    }

    public static Object getStaticFinalField(Class classe, String nomChamp) throws IllegalAccessException {
        try {
            Field field = classe.getField(nomChamp);
            return field.get(null);
        } catch (NoSuchFieldException exception) {
            Assertions.fail("Aucun champ nommé " + nomChamp + " n'a été trouvé");
        }
        return null;
    }

    public static void checkPrivateField(Class classe, String nomChamp, Class type) throws IllegalAccessException {
        Field field = null;
        try {
            field = classe.getDeclaredField(nomChamp);
            Assertions.assertThat(field.getType()).isEqualTo(type);
        } catch (NoSuchFieldException exception) {
            Assertions.fail("Aucun champ nommé " + nomChamp + " n'a été trouvé");
        }
        Assertions.assertThat(Modifier.isPrivate(field.getModifiers())).as("Le champ " + nomChamp + " n'est pas privé").isTrue();
        Assertions.assertThat(Modifier.isFinal(field.getModifiers())).as("Le champ " + nomChamp + " ne doit pas être final").isFalse();
    }

    public static void checkAbstractClass(Class classe) throws IllegalAccessException {
        Assertions.assertThat(Modifier.isAbstract(classe.getModifiers())).as("La classe " + classe.getName() + " n'est pas abstraite").isTrue();
        Assertions.assertThat(Modifier.isPublic(classe.getModifiers())).as("La classe " + classe.getName() + " n'est pas publique").isTrue();
    }

    public static void checkNotAbstractClass(Class classe) throws IllegalAccessException {
        Assertions.assertThat(Modifier.isAbstract(classe.getModifiers())).as("La classe " + classe.getName() + " est abstraite").isFalse();
    }


    public static void invokeSetter(Object obj, String variableName, Object variableValue) throws Exception{
      /* variableValue is Object because value can be an Object, Integer, String, etc... */
        /**
         * Get object of PropertyDescriptor using variable name and class
         * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
         */
        PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
         /* Set field/variable value using getWriteMethod() */
        objPropertyDescriptor.getWriteMethod().invoke(obj, variableValue);
    }

    public static Object invokeGetter(Object obj, String variableName){
        try {
            /**
             * Get object of PropertyDescriptor using variable name and class
             * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
             */
            PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
            /**
             * Get field/variable value using getReadMethod()
             * variableValue is Object because value can be an Object, Integer, String, etc...
             */
            Object variableValue = objPropertyDescriptor.getReadMethod().invoke(obj);
        /* Print value of variable */
            return variableValue;
        } catch (Exception e) {
            Assertions.fail("Impossible d'appeler le getter");
        }
        return null;
    }


}
