package com.shangyong.backend.utils;


import javax.xml.bind.annotation.XmlElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.List;


public class CreateTable {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
//createTable(User.class, null);
// createTable(Book.class, null);
        List<Class<?>> class1 = PackageUtil.getClass("com.shangyong.backend.entity.approval", true);
        for (Class<?> class2 : class1) {
            createTable(class2, null);
        }
    }
    public static void createTable(Class obj,String tableName) throws IOException{




        Field[] fields = null;
        fields = obj.getDeclaredFields();
        Class annotationType = null;
        Object param = null;
        String column = null;
        XmlElement xmlElement = null;
        StringBuilder sb = null;
        sb = new StringBuilder(50);
        if(tableName==null||tableName.equals("")){
//未传表明默认用类名
            tableName = obj.getName();
            tableName = tableName.substring(tableName.lastIndexOf(".")+1);
        }
        sb.append("\r\ndrop table if exists  ").append(tableName).append(";\r\n");
        sb.append("create table ").append(tableName).append(" ( \r\n");
        System.out.println(tableName);
        boolean firstId = true;
        File file = null;
        int count = 0;
        for(Field f:fields){
            column = f.getName();
            column = columnToUpperCase(column);
            sb.append(column).append(" ");
            System.out.println(column+","+f.getType());
            param = f.getType();
            String string = f.getType().toString();
            String substring = string.substring(string.lastIndexOf(".")+1);
//sb.append(column);//一般第一个是主键
            if(substring.equals("String")){
                sb.append(" VARCHAR(50) ");
            }else {
                sb.append(substring);//根据需要自行修改
            }
            if(firstId){//类型转换
                sb.append(" PRIMARY KEY AUTO_INCREMENT ");
                firstId = false;
            }
//获取字段中包含fieldMeta的注解  
//2、获取属性上的所有注释
/*Annotation[] allAnnotations = f.getAnnotations();
for(Annotation an : allAnnotations){
sb.append(" COMMIT '");
xmlElement = (XmlElement)an;
annotationType = an.annotationType();
param = ((XmlElement) an).name();
System.out.println("属性【"+f.getName()+"-----的注释类型有: " + param);
sb.append(param).append("'");
}*/
            count++;
            int length = fields.length;
            if (count <length) {
                sb.append(",\n ");
            }
        }
        String sql = null;
        sql = sb.toString();
        sql = sb.substring(0, sql.length()-1)+" )ENGINE =INNODB DEFAULT CHARSET= utf8;\r\n";
        file = new File("WebContent/createTable/建表.txt");
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
            }
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println("文件路径:"+file.getAbsolutePath());
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
        out.write(sql) ;
        out.flush();
        out.close() ;


    }

    public static String columnToUpperCase(String column) {
        char[] columnArr = column.toCharArray();
        int index = -1;
        for (int i = 1; i < columnArr.length; i++) {
            if (columnArr[i] >= 'A' && columnArr[i] <= 'Z') {
                index = i;
                break;
            }
        }
        if (index == -1) {
            column = column.toUpperCase();
            return column;
        }else {
            column = (column.substring(0, index)+"_"+columnToUpperCase(column.substring(index))).toUpperCase();
        }

        return column;
    }


}
