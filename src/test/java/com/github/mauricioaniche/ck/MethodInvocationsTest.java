package com.github.mauricioaniche.ck;

import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.google.common.collect.Sets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MethodInvocationsTest extends BaseTest {
    private String qualifier = "MethodInvocation.Complex1.";
    static private String javaLang1 = "m3/1[java.lang.String]";
    static private String javaLang2 = "m4/2[java.lang.String,int]";
    @BeforeAll
    public void setUp() {
        report = run(fixturesDir() + "/MethodInvocation");
    }

    //Test method invocations
    @Test
    public void basicMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m1 = ckClass.getMethod("m1/0").get();
        assertEquals(
                Sets.newHashSet(qualifier + "m1/0", qualifier + "m2/1[int]", "MethodInvocation.Simple2.x/0"),
                m1.getMethodInvocations());

        CKMethodResult m2 = ckClass.getMethod("m2/1[int]").get();
        assertEquals(
                Sets.newHashSet(qualifier + javaLang1, "MethodInvocation.GO2.magic/0"),
                m2.getMethodInvocations());

        CKMethodResult m3 = ckClass.getMethod(javaLang1).get();
        assertEquals(
                Sets.newHashSet("java.io.PrintStream.println/1[int]", qualifier + javaLang2),
                m3.getMethodInvocations());

        CKMethodResult m4 = ckClass.getMethod(javaLang2).get();
        assertEquals(
                Sets.newHashSet("MethodInvocation.Simple2.x/0", qualifier + "m2/1[int]"),
                m4.getMethodInvocations());
    }

    @Test
    public void ifBlockMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m5 = ckClass.getMethod("m5/0").get();
        assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]"),
                m5.getMethodInvocations());
    }

    @Test
    public void returnMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m6 = ckClass.getMethod("m6/0").get();
        assertEquals(
                Sets.newHashSet(qualifier + javaLang2),
                m6.getMethodInvocations());
    }

    @Test
    public void staticMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m7 = ckClass.getMethod("m7/0").get();
        assertEquals(
                Sets.newHashSet("MethodInvocation.GO2.boring/0"),
                m7.getMethodInvocations());
    }

    @Test
    public void lambdaMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m8 = ckClass.getMethod("m8/0").get();
        assertEquals(
                Sets.newHashSet("java.util.Collection<java.lang.String>.stream/0",
                        "java.util.stream.Stream<java.lang.String>.map/1[java.util.function.Function<? super java.lang.String,? extends java.lang.Integer>]",
                        "MethodInvocation.Simple2.x/0",
                        "java.util.stream.Collectors.toList/0",
                        "java.util.stream.Stream<java.lang.Integer>.collect/1[java.util.stream.Collector<? super java.lang.Integer,java.lang.Object,java.util.List<java.lang.Integer>>]",
                        qualifier + javaLang2),
                m8.getMethodInvocations());
    }

    @Test
    public void anonymousClassMethodInvocations(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m9 = ckClass.getMethod("m9/0").get();
        assertEquals(
                Sets.newHashSet("MethodInvocation.Complex1.Complex2.n1/0"),
                m9.getMethodInvocations());
    }

    //Test local method invocations
    @Test
    public void basicMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m1 = ckClass.getMethod("m1/0").get();
        assertEquals(
                Sets.newHashSet(qualifier + "m1/0", qualifier + "m2/1[int]"),
                m1.getMethodInvocationsLocal());

        CKMethodResult m2 = ckClass.getMethod("m2/1[int]").get();
        assertEquals(
                Sets.newHashSet(qualifier + javaLang1),
                m2.getMethodInvocationsLocal());

        CKMethodResult m3 = ckClass.getMethod(javaLang1).get();
        assertEquals(
                Sets.newHashSet(qualifier + javaLang2),
                m3.getMethodInvocationsLocal());

        CKMethodResult m4 = ckClass.getMethod(javaLang2).get();
        assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]"),
                m4.getMethodInvocationsLocal());
    }

    @Test
    public void ifBlockMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m5 = ckClass.getMethod("m5/0").get();
        assertEquals(
                Sets.newHashSet(qualifier + "m2/1[int]"),
                m5.getMethodInvocationsLocal());
    }

    @Test
    public void returnMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m6 = ckClass.getMethod("m6/0").get();
        assertEquals(
                Sets.newHashSet(qualifier + javaLang2),
                m6.getMethodInvocationsLocal());
    }

    @Test
    public void staticMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m7 = ckClass.getMethod("m7/0").get();
        assertEquals(
                Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY),
                m7.getMethodInvocationsLocal());
    }

    @Test
    public void lambdaMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m8 = ckClass.getMethod("m8/0").get();
        assertEquals(
                Sets.newHashSet(qualifier + javaLang2),
                m8.getMethodInvocationsLocal());
    }

    @Test
    public void anonymousClassMethodInvocationsLocal(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m9 = ckClass.getMethod("m9/0").get();
        assertEquals(
                Sets.newHashSet(ArrayUtils.EMPTY_STRING_ARRAY),
                m9.getMethodInvocationsLocal());
    }

    //Test indirect local method invocations
    @Test
    public void basicMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        CKMethodResult m1 = ckClass.getMethod("m1/0").get();

        HashMap<String, Set<String>> invocations1 = new HashMap<>();
        invocations1.put(qualifier + "m1/0", Sets.newHashSet(qualifier + "m2/1[int]"));
        invocations1.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + javaLang1));
        invocations1.put(qualifier + javaLang1, Sets.newHashSet(qualifier + javaLang2));
        assertEquals(invocations1, m1.getMethodInvocationsIndirectLocal());

        CKMethodResult m2 = ckClass.getMethod("m2/1[int]").get();
        HashMap<String, Set<String>> invocations2 = new HashMap<>();
        invocations2.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + javaLang1));
        invocations2.put(qualifier + javaLang1, Sets.newHashSet(qualifier + javaLang2));
        assertEquals(invocations2, m2.getMethodInvocationsIndirectLocal());

        HashMap<String, Set<String>> invocations3 = new HashMap<>();
        invocations3.put(qualifier + javaLang1, Sets.newHashSet(qualifier + javaLang2));
        invocations3.put(qualifier + javaLang2, Sets.newHashSet(qualifier + "m2/1[int]"));
        CKMethodResult m3 = ckClass.getMethod(javaLang1).get();
        assertEquals(invocations3, m3.getMethodInvocationsIndirectLocal());

        HashMap<String, Set<String>> invocations4 = new HashMap<>();
        invocations4.put(qualifier + javaLang2, Sets.newHashSet(qualifier + "m2/1[int]"));
        invocations4.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + javaLang1));
        CKMethodResult m4 = ckClass.getMethod(javaLang2).get();
        assertEquals(invocations4, m4.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void ifBlockMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        HashMap<String, Set<String>> invocations5 = new HashMap<>();
        invocations5.put(qualifier + "m5/0", Sets.newHashSet(qualifier + "m2/1[int]"));
        invocations5.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + javaLang1));
        invocations5.put(qualifier + javaLang1, Sets.newHashSet(qualifier + javaLang2));
        CKMethodResult m5 = ckClass.getMethod("m5/0").get();
        assertEquals(invocations5, m5.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void returnMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        HashMap<String, Set<String>> invocations6 = new HashMap<>();
        invocations6.put(qualifier + "m6/0", Sets.newHashSet(qualifier + javaLang2));
        invocations6.put(qualifier + javaLang2, Sets.newHashSet(qualifier + "m2/1[int]"));
        invocations6.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + javaLang1));
        CKMethodResult m6 = ckClass.getMethod("m6/0").get();
        assertEquals(invocations6, m6.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void staticMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        HashMap<String, Set<String>> invocations7 = new HashMap<>();
        CKMethodResult m7 = ckClass.getMethod("m7/0").get();
        assertEquals(invocations7, m7.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void lambdaMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        HashMap<String, Set<String>> invocations8 = new HashMap<>();
        invocations8.put(qualifier + "m8/0", Sets.newHashSet(qualifier + javaLang2));
        invocations8.put(qualifier + javaLang2, Sets.newHashSet(qualifier + "m2/1[int]"));
        invocations8.put(qualifier + "m2/1[int]", Sets.newHashSet(qualifier + javaLang1));
        CKMethodResult m8 = ckClass.getMethod("m8/0").get();
        assertEquals(invocations8, m8.getMethodInvocationsIndirectLocal());
    }

    @Test
    public void anonymousClassMethodInvocationsLocalIndirect(){
        CKClassResult ckClass = report.get("MethodInvocation.Complex1");
        HashMap<String, Set<String>> invocations9 = new HashMap<>();
        CKMethodResult m9 = ckClass.getMethod("m9/0").get();
        assertEquals(invocations9, m9.getMethodInvocationsIndirectLocal());
    }
}