package badWeatherApp.serverUtility.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserializerTest {

    public static class TestClass{
        private int id;
        private String property;
        private double value;

        public TestClass() {
        }

        public TestClass(int id, String property, double value) {
            this.id = id;
            this.property = property;
            this.value = value;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public void setValue(double value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "TestClass{" +
                    "id=" + id +
                    ", property='" + property + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @Test
    void shouldCreateTestClassObjectFromJsonString(){
        String JsonString = "{ \"id\" : 54, \"property\" : \"visible=true\", \"value\" : 65.34 }";
        try {
            TestClass testClass = JsonDeserializer.deserialize(JsonString, TestClass.class);
            System.out.println(testClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}