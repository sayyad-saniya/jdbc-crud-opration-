//image insert and retrive in jdbc

import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class imagehandle {
    public static void main(String[] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";
     //   String folder_path="C:\\Users\\Admin\\Desktop\\course\\AI\\jdbc.jfif";
       // String query ="INSERT INTO image_table(image_data) VALUES(?)";(two lines for insert the image)

        String folder_path="C:\\Users\\Admin\\Desktop\\course\\AI\\image\\";
        String query="SELECT image_data from image_table where image_id=(?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver loaded");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try {
            Connection con =DriverManager.getConnection(url,username,password);
            System.out.println("connect successfully");
            PreparedStatement preparedStatement= con.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                byte[] image_data= resultSet.getBytes("image_data");
                String image_path=folder_path+"extractedImage.jpg";
                OutputStream outputStream=new FileOutputStream(image_path);

                outputStream.write(image_data);
                System.out.println("found");

            }else {
                System.out.println("image not found");
            }

            /*FileInputStream fileInputStream= new FileInputStream(image_path);
            byte[] imageData= new byte[fileInputStream.available()];

            //here we use byte array for store the image
            // available show how much data is available in byte

            fileInputStream.read(imageData);
            PreparedStatement preparedStatement= con.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);
            int AffectedRows=preparedStatement.executeUpdate();
            if(AffectedRows>0){
                System.out.println("insert image");
            }else{
                System.out.println("failed to insert image");
            }(for insert image use)*/





        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
            // above catch use for fileinputstream
        }
    }
}