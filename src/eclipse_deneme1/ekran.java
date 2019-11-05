package eclipse_deneme1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;

import com.lowagie.text.Image;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import net.sourceforge.tess4j.Tesseract;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.imageio.ImageIO;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.Component;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ekran extends JFrame {

	
	static Tesseract tesseract = new Tesseract();
	  
	  static {
		    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		    tesseract.setDatapath("C:/Program Files (x86)/Tesseract-OCR/tessdata/");
		    tesseract.setLanguage("tur");
		  }
	private JPanel contentPane;
	private JTextField firmaadi_textF;
	private JTextField tarih_textF;
	private JTextPane  parsingtxt;
	private JTable table;
	private JTable fis_table;
	DefaultTableModel fis_model=new DefaultTableModel();
	DefaultTableModel urun_model=new DefaultTableModel();
	Object[] kolonlar  = {"ID", "Firma Ad\u0131", "Tarih", "Saat ", "Fi\u015F No", "Toplam KDV", "Toplam Fiyat"};
	Object[] urun_kolonlar  = {"ID", "Fiþ ID", "Ürün Adý", "%KDV ", "Fiyat"};
	
	private JTable table_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ekran frame = new ekran();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Fis_listele(){
		fis_model.setRowCount(0);
	              ArrayList<plug> listele=new ArrayList<>();
	              
	           listele=new veri_tabani().Plug_List();
	                if(listele!=null){
	                    for(plug x:listele){
	                    Object[] eklenecek ={x.getId(),x.getCompany_name(),x.getDate(),x.getTime(),x.getPlug_no(),x.getTotal_kdv(),x.getTotal_price()};
	                    fis_model.addRow(eklenecek);
	                    }
	                }
	               
	    }
	public void Fis_filterele_listele(String firma_adi,String tarih){
		fis_model.setRowCount(0);
	              ArrayList<plug> listele=new ArrayList<>();
	              
	           listele=new veri_tabani().Plug_List();
	                if(listele!=null){
	                    for(plug x:listele){
	                    	if(firma_adi!=null) {
	                    		if(x.getCompany_name().contains(firma_adi)) {
	                    		}else {
	                    			continue;
	                    		}
	                    	}
	                    	if(tarih!=null) {
	                    		if(x.getDate().contains(tarih)) {
	                    		}else {
	                    			continue;
	                    		}
	                    	}
	                    Object[] eklenecek ={x.getId(),x.getCompany_name(),x.getDate(),x.getTime(),x.getPlug_no(),x.getTotal_kdv(),x.getTotal_price()};
	                    fis_model.addRow(eklenecek);
	                    }
	                }
	               
	    }
	
	public void Urun_listele(int plug_id){
		urun_model.setRowCount(0);
	              ArrayList<product> listele=new ArrayList<>();
	              
	           listele=new veri_tabani().Product_List(plug_id);
	                if(listele!=null){
	                    for(product x:listele){
	                    Object[] eklenecek ={x.getId(),x.getPlug_id(),x.getName(),x.getKDV(),x.getPrice()};
	                    urun_model.addRow(eklenecek);
	                    }
	                }
	               
	    }
	public ekran() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 835);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 13, 380, 305);
		contentPane.add(panel);
		panel.setLayout(null);
		Fis_listele();
		
		
		JButton btnResimSec = new JButton("Resim Se\u00E7");
		btnResimSec.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// BURADA MOUSE TIKLANDIÐINDA RESÝM SEÇTÝRME EKRANI AÇILACAK
				JFileChooser j = new JFileChooser("D:/calismalarim/eclipse/fis"); 
				
				// Open the save dialog 
				j.showSaveDialog(null); 
				//System.out.println(j.getSelectedFile().getAbsolutePath());
				//System.out.println(j.getSelectedFile().getParent());
				String url1=j.getSelectedFile().getAbsolutePath();
				
				RecognizeText recognizeText=new RecognizeText();
				String pars_text=recognizeText.Cevir4(url1);
				parsingtxt.setText(pars_text);
				text_edit A =new text_edit();
				A.KIRP(pars_text,url1);
				Fis_listele();
			}
		});
		btnResimSec.setBounds(58, 254, 228, 51);
		panel.add(btnResimSec);
		
		parsingtxt = new JTextPane();
		parsingtxt.setEditable(false);
		parsingtxt.setDropMode(DropMode.INSERT);
		parsingtxt.setText("Fi\u015F Resmi Se\u00E7iniz");
		parsingtxt.setBounds(253, 170, 263, 90);
	//	panel.add(parsingtxt);
		
		JScrollPane scrollPane = new JScrollPane(parsingtxt);
		scrollPane.setBounds(12, 5, 338, 236);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(419, 230, 408, 79);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblArama = new JLabel("Arama");
		lblArama.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArama.setBounds(179, 13, 56, 16);
		panel_1.add(lblArama);
		
		JLabel lblFirmaAd = new JLabel("Firma Ad\u0131:");
		lblFirmaAd.setBounds(28, 42, 78, 16);
		panel_1.add(lblFirmaAd);
		
		JLabel lblTarih = new JLabel("Tarih:");
		lblTarih.setBounds(225, 45, 56, 16);
		panel_1.add(lblTarih);
		
		firmaadi_textF = new JTextField();
		firmaadi_textF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					String firma_adi=firmaadi_textF.getText();
					String tarih=tarih_textF.getText();
					Fis_filterele_listele(firma_adi,tarih);
				
			}
		});
		firmaadi_textF.setBounds(92, 39, 116, 22);
		panel_1.add(firmaadi_textF);
		firmaadi_textF.setColumns(10);
		
		tarih_textF = new JTextField();
		tarih_textF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firma_adi=firmaadi_textF.getText();
				String tarih=tarih_textF.getText();
				Fis_filterele_listele(firma_adi,tarih);
			}
		});
		tarih_textF.setBounds(266, 42, 116, 22);
		panel_1.add(tarih_textF);
		tarih_textF.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(37, 331, 775, 223);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblTmBilgiler = new JLabel("T\u00FCm Bilgiler");
		lblTmBilgiler.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTmBilgiler.setBounds(331, 27, 104, 16);
		panel_2.add(lblTmBilgiler);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 56, 753, 154);
		panel_2.add(scrollPane_1);
		
	
		fis_table = new JTable();
		fis_table.addMouseListener(new MouseAdapter() {
	
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int secili_row=Integer.valueOf(fis_model.getValueAt(fis_table.getSelectedRow(),0).toString());
				Urun_listele(secili_row);
			}
		});
		scrollPane_1.setViewportView(fis_table);
		fis_model.setColumnIdentifiers(kolonlar);
		fis_model.setRowCount(0);
		  ArrayList<plug> listele=new ArrayList<>();
          
          listele=new veri_tabani().Plug_List();
               if(listele!=null){
                   for(plug x:listele){
                   Object[] eklenecek ={x.getId(),x.getCompany_name(),x.getDate(),x.getTime(),x.getPlug_no(),x.getTotal_kdv(),x.getTotal_price()};
                   fis_model.addRow(eklenecek);
                   }
               }
		
		fis_table.setModel(fis_model);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(47, 567, 765, 164);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(71, 13, 613, 138);
		panel_3.add(scrollPane_2);
		
		table_1 = new JTable();
		scrollPane_2.setViewportView(table_1);
		urun_model.setColumnIdentifiers(urun_kolonlar);
		urun_model.setRowCount(0);
		 /* ArrayList<plug> listele=new ArrayList<>();
          
          listele=new veri_tabani().Plug_List();
               if(listele!=null){
                   for(plug x:listele){
                   Object[] eklenecek ={x.getId(),x.getCompany_name(),x.getDate(),x.getTime(),x.getPlug_no(),x.getTotal_kdv(),x.getTotal_price()};
                   fis_model.addRow(eklenecek);
                   }
               }*/
		
       table_1.setModel(urun_model);
	
	
	}
}
