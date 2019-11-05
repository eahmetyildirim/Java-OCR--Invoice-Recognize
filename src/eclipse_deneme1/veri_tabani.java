package eclipse_deneme1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class veri_tabani {

	private String kullanici_adi = "root";
    private String parola = "";
    private Connection con = null;
    
    private Statement sorgum = null;
    private PreparedStatement preparedStatement = null;
    
    
    public veri_tabani(){
        String url = "jdbc:mysql://localhost:3306/yazlab11?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
        
     
     try {
         
         Class.forName("com.mysql.cj.jdbc.Driver");
   
         
     } catch (ClassNotFoundException ex) {
         System.out.println("Driver Bulunamadý....");
     }
     try {
         con =  (Connection) DriverManager.getConnection(url, kullanici_adi, parola);
           
         
     } catch (SQLException ex) {
         System.out.println("Baðlantý Baþarýsýz...");
         ex.printStackTrace();
     }
    
  }
    
public ArrayList<company> Company_list() {
        
	ArrayList<company> cikti = new ArrayList<company>();
        
        try {
            sorgum =  (Statement) con.createStatement();
            String sorgu =  "Select * From companies";
            
            ResultSet rs =  sorgum.executeQuery(sorgu);
            
            while(rs.next()) {
            	int company_id=rs.getInt("id");
                String company_name = rs.getString("company_name");
                String search_text = rs.getString("search_text");
               
                cikti.add(new company(company_id,company_name,search_text));
                
                
            }
            return cikti;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(veri_tabani.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
         
    }// FÝRMA ADI LÝSTELEME SONU

public String CompanyId_CompanyName(int id){
    try {
        
         sorgum =  (Statement) con.createStatement();
         String sorgu =  "Select * From companies where id="+id+"";
         
         ResultSet rs =  sorgum.executeQuery(sorgu);
         String name = null;
         while(rs.next()) {
              name = rs.getString("company_name");
                
         }
         return name;
         
         
         
     } catch (SQLException ex) {
         Logger.getLogger(veri_tabani.class.getName()).log(Level.SEVERE, null, ex);
         return null;
         
     }

 }

public ArrayList<plug> Plug_List() {
    
	ArrayList<plug> cikti = new ArrayList<plug>();
        
        try {
            sorgum =  (Statement) con.createStatement();
            String sorgu =  "Select * From plug";
            
            ResultSet rs =  sorgum.executeQuery(sorgu);
            
            while(rs.next()) {
            	int id=rs.getInt("id");
                int company_id = rs.getInt("company_id");
                String date = rs.getString("date");
                String time = rs.getString("time");
                int plug_no = rs.getInt("plug_no");
                double total_kdv = rs.getDouble("total_KDV");
                double total_price = rs.getDouble("total_price");
                String company_name=CompanyId_CompanyName(company_id);
                cikti.add(new plug(id,company_id,company_name,date,time,plug_no,total_kdv,total_price));
                
                
            }
            return cikti;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(veri_tabani.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
         
    }// FÝÞ  LÝSTELEME SONU

public ArrayList<product> Product_List(int plug_id) {
    
	ArrayList<product> cikti = new ArrayList<product>();
        
        try {
         /*   sorgum =  (Statement) con.createStatement();
            String sorgu =  "Select * From plug_products where plug_id_"+plug_id+"";
            
            ResultSet rs =  sorgum.executeQuery(sorgu);
           */ 
            String sorgu =  "Select * From plug_products where plug_id=?";
            
            preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
            preparedStatement.setInt(1, plug_id);
           
            ResultSet rs= preparedStatement.executeQuery();
            
            while(rs.next()) {
            	int id=rs.getInt("id");
                String name = rs.getString("product_name");
                int kdv = rs.getInt("KDV");
                double price = rs.getDouble("price");
                cikti.add(new product(name, kdv, price, plug_id, id));
                
                
            }
            return cikti;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(veri_tabani.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
         
    }


public int getPlug_ID(int company_id,int plug_no){
    try {
          String sorgu =  "Select * From plug where company_id=? and plug_no=?";
       
         preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
         preparedStatement.setInt(1, company_id);
         preparedStatement.setInt(2, plug_no);
         ResultSet rs= preparedStatement.executeQuery();
       int id=0;
         while(rs.next()) {
              id=rs.getInt("id");
           
         }
         return id;
         
         
         
     } catch (SQLException ex) {
         Logger.getLogger(veri_tabani.class.getName()).log(Level.SEVERE, null, ex);
         return 0;
         
 }
 
 }


public boolean plug_add(int company_id,String plug_date,String plug_time,int plug_no,double total_kdv,double total_price){
	String sorgu = "Insert Into plug (company_id,date,time,plug_no,total_KDV,total_price) VALUES(?,?,?,?,?,?)";
     
    try {
        preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
        
        
        preparedStatement.setInt(1, company_id);
        preparedStatement.setString(2, plug_date);
        preparedStatement.setString(3, plug_time);
        preparedStatement.setInt(4, plug_no);
        preparedStatement.setDouble(5,total_kdv);
        preparedStatement.setDouble(6,total_price);
        preparedStatement.executeUpdate();
        return true;
        
        
    } catch (SQLException ex) {
        Logger.getLogger(veri_tabani.class.getName()).log(Level.SEVERE, null, ex);
    }
     
     return false;
 
 }

public boolean product_add(int plug_id,String name,int kdv,double price){
	String sorgu = "Insert Into plug_products (plug_id,product_name,KDV,price) VALUES(?,?,?,?)";
     
    try {
        preparedStatement = (PreparedStatement) con.prepareStatement(sorgu);
        
        
        preparedStatement.setInt(1, plug_id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, kdv);
        preparedStatement.setDouble(4, price);
        preparedStatement.executeUpdate();
        return true;
        
        
    } catch (SQLException ex) {
        Logger.getLogger(veri_tabani.class.getName()).log(Level.SEVERE, null, ex);
    }
     
     return false;
 
 }

	
}
