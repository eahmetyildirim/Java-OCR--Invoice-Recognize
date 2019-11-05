package eclipse_deneme1;

import java.util.ArrayList;
import java.util.Map;

import javax.sound.midi.Soundbank;

public class text_edit {
	private String company_name;
	private int company_id;
	private int plug_id;
	private String plug_date;
	private String plug_time;
	private int plug_no;
	private double total_price;
	private double total_KDV;
	private ArrayList<product> product_list;
	private int bulunan[]= {0,0,0,0};
	private int bulunan_i[]= {-1,-1,-1,-1};
	private int deneme=0;
	
	
	
public text_edit() {
	product_list=new ArrayList<product>();
	total_KDV=-1;
	total_price=-1;
	}
public void Fis_Tarih_Bul(char satir[],int i) {
		
		for(int j=0;j<satir.length;j++) {
			if(Character.isDigit(satir[j])) {
				if(Character.isDigit(satir[j+1])) {
					if(satir[j+2]=='.' || satir[j+2]=='/'|| satir[j+2]==',') {
							satir[j+2]='/';
						
						if(Character.isDigit(satir[j+3])) {
							if(Character.isDigit(satir[j+4])) {
								if(satir[j+5]=='.' || satir[j+5]=='/'|| satir[j+5]==',') {
											satir[j+5]='/';
									if(Character.isDigit(satir[j+6])) {
										if(Character.isDigit(satir[j+7])) {
											if(Character.isDigit(satir[j+8])) {
												if(Character.isDigit(satir[j+9])) {
													char fis_tarih[]=new char[10];
													for(int z=0;z<10;z++) {
														fis_tarih[z]=satir[j+z];
													}
													plug_date=new String(fis_tarih);
													bulunan_i[1]=i;
													bulunan[1]=1;
												}
											}
										}
									}
								}
							}
						}	
					}
				}
			}
		}
		
		
	}
public void Fis_Saat_Bul(char satir[],int i) {
		char fis_saat[]=new char[8];
		for(int j=0;j<satir.length;j++) {
			if(Character.isDigit(satir[j])) {
				if(Character.isDigit(satir[j+1])) {
					if(satir[j+2]==':') {
						if(Character.isDigit(satir[j+3])) {
							if(Character.isDigit(satir[j+4])) {
								if(satir[j+5]==':') {
									if(Character.isDigit(satir[j+6])) {
										if(Character.isDigit(satir[j+7])) {
													for(int z=0;z<8;z++) {
														fis_saat[z]=satir[j+z];
													}
													plug_time=new String(fis_saat);
													bulunan[2]=1;
													bulunan_i[2]=i;
													break;
											
										}
									}
								}else {
									
									for(int z=0;z<5;z++) {
										fis_saat[z]=satir[j+z];
									}
									fis_saat[5]=':';
									fis_saat[6]='0';
									fis_saat[7]='0';
									plug_time=new String(fis_saat);
									
									bulunan[2]=1;
									bulunan_i[2]=i;
									break;
								}
							}
						}	
					}
				}
			}
		}
		
	}
public void Fis_Saat_Bul2(char satir[],int i) {
	char fis_saat[]=new char[8];	
	for(int j=0;j<satir.length;j++) {
		if(new String(satir).toLowerCase().contains("saat")) {
			Fis_Saat_Bul(satir,i);
			
		}
	}

	
	
}
public void Fis_No_Bul(char satir[],int i) {
	
	 int fis_no=0;
	for(int j=0;j<satir.length;j++) {
		if(satir[j]=='F' || satir[j]=='f') {
			if(satir[j+1]=='I' || satir[j+1]=='Ý'|| satir[j+1]=='i'|| satir[j+1]=='ý') {
				if(satir[j+2]=='Þ' || satir[j+2]=='þ'|| satir[j+2]=='S'|| satir[j+2]=='s') {
					
							
							int k=0;
							if(satir[j+3]==' ') { 
							
								while(satir[j+3+k]==' ') {
								k++;
								}
							}
							k+=3;
						if(satir[j+k]=='N' || satir[j+k]=='n') {
							if(satir[j+k+1]=='O' || satir[j+k+1]=='o') {
								j=j+k+2;
									if(satir.length>j) {
								while(true!=Character.isDigit(satir[j])) {
									j++;
									if(j>=satir.length) {
										return;
									}
									}
								bulunan[3]=1;
								bulunan_i[3]=i;
								int z=0;
								int sif_buyuk=0;
								while(true==Character.isDigit(satir[j])) {
									if(satir[j]=='0' && sif_buyuk==0 ) {
										j++;
										continue;
									}
									if(sif_buyuk==0 && satir[j]!='0') {
										sif_buyuk++;
									}
									fis_no=fis_no*10+Integer.parseInt(String.valueOf(satir[j]));	
									z++;
									j++;
									}
								plug_no=fis_no;
							
								break;
								}
							}
						}
								
					}	
				}
			}
		}
		
	
}
public int son_indis(int[] indis) {
	int enb=indis[0];
	for(int i=0;i<4;i++) {
		if(enb<indis[i])
			enb=indis[i];
	}
	return enb;
	
}

public void Fis_UrunlerKdvToplam_Bul(ArrayList<String> fis_verisi) {

	ArrayList<product> urunler =new ArrayList<>();
			for(int i=son_indis(bulunan_i)+1;i<fis_verisi.size();i++) {
			char satir[]=fis_verisi.get(i).toCharArray();
			
			if(fis_verisi.get(i).contains("X") || fis_verisi.get(i).contains("x")|| fis_verisi.get(i).contains("*")|| fis_verisi.get(i).contains("&")|| fis_verisi.get(i).contains("%")) {
				if(fis_verisi.get(i).contains("TOPKDV")==false && fis_verisi.get(i).contains("TOPLAM")==false && fis_verisi.get(i).contains("%")) {
					String[] kirp=fis_verisi.get(i).split("%");
					
					if(kirp.length<2) {
						continue;
					}
					String urun_adi=kirp[0];
					char[] S=kirp[1].toCharArray();
					
					int j=0,kdv=0;
					char[] urun_fiyat=new char[20];
					int kontrol=0;
if(S.length-1==j || S.length==j) {
						
						continue;
					}
					while(!Character.isDigit(S[j])) {
						j++;
						if(S.length-1==j) {
							kontrol++;
							break;
						}
					}
					if(kontrol!=0) {
						continue;
					}
					if(S.length-1==j) {
						
						continue;
					}
					
					
					while(Character.isDigit(S[j])) {
					kdv=kdv*10+Integer.parseInt(String.valueOf(S[j]));
					j++;
					if(S.length-1==j) {
						
						break;
					}
					}
					
					if(S.length-1==j) {
						
						continue;
					}
					while(!Character.isDigit(S[j])) {
						j++;
						if(S.length-1==j) {
							kontrol++;
							break;
						}
					}
					if(kontrol!=0) {
						continue;
					}
					int z=0;
					while(Character.isDigit(S[j])) {
						
						urun_fiyat[z]=S[j];
						j++;
						z++;
						if(S[j]==',') {
							j++;
						}
					}
					if(z<3) {
						//i++;
						continue;
					}
					urun_fiyat[z]=urun_fiyat[z-1];
					urun_fiyat[z-1]=urun_fiyat[z-2];
					urun_fiyat[z-2]='.';
				
						String[] urun_fiyat_parca=String.valueOf(urun_fiyat).split(" ");
					
					double urun_fiyati=Double.valueOf(urun_fiyat_parca[0]);
					product yeni_veri=new product();
					yeni_veri.setName(urun_adi);
					yeni_veri.setKDV(kdv);
					yeni_veri.setPrice(urun_fiyati);
					
					urunler.add(yeni_veri);
				}
				
			}// FÝÞ TEKÝ ÜRÜNLERÝ EKLEME SONU
		
			if(fis_verisi.get(i).contains("TOPKDV") && total_price==-1) {
					
				int satir1=-1;
					if(fis_verisi.get(i).contains("*")) {
						satir1=	fis_verisi.get(i).indexOf("*")+1;	
					}else if(fis_verisi.get(i).contains("X")) {
						satir1=	fis_verisi.get(i).indexOf("X")+1;		
					}else if(fis_verisi.get(i).contains("x")) {
						satir1=	fis_verisi.get(i).indexOf("x")+1;		
					}else {
						continue;
					}
					char kdv_fiyat[] =new char[20];
					int k=0;
					int nokta_say=0;
				for(int z=satir1;z<satir.length;z++) {
					if(satir[z]==',' || Character.isDigit(satir[z])) {
					if(satir[z]==',') {
						satir[z]='.';
						nokta_say++;
					}
					if(nokta_say>1) {
						break;
					}
					kdv_fiyat[k]=satir[z];
					k++;
					}else {
						break;
					}
				}
				if(k!=0) {
				String[] parcala=new String(kdv_fiyat).split(" ");
			
				total_KDV=Double.valueOf(parcala[0]);
				}
			}
			
			
			if(fis_verisi.get(i).contains("TOPLAM")) {
				int satir1=-1;
				if(fis_verisi.get(i).contains("*")) {
					satir1=	fis_verisi.get(i).indexOf("*")+1;	
				}else if(fis_verisi.get(i).contains("X")) {
					satir1=	fis_verisi.get(i).indexOf("X")+1;		
				}else if(fis_verisi.get(i).contains("x")) {
					satir1=	fis_verisi.get(i).indexOf("x")+1;		
				}else {
					continue;
				}
				char[] toplam_fiyat=new char[20];
				int k=0;
			for(int z=satir1;z<satir.length;z++) {
				
				if(satir[z]==',' || Character.isDigit(satir[z])) {
					if(satir[z]==',') {
						satir[z]='.';
					}
					toplam_fiyat[k]=satir[z];
					k++;
					
				}else {
					break;
				}
			}
			
			if(k!=0) {
				String[] parcala=new String(toplam_fiyat).split(" ");
				
				total_price=Double.valueOf(parcala[0]);
				}
			
			
			if(urunler.size()>product_list.size()) {
				product_list.clear();
				product_list=(ArrayList<product>) urunler.clone();
				}
				break;
			}
		
	

	}
	
	
	
		
}



public void KIRP(String X,String url) {
		ArrayList<String> fis_verisi=new ArrayList<String>();
		char[] Dizi =X.toCharArray();
		int boyut=Dizi.length;
		int sayac=0;
		for(int i=0;i<boyut;i++) {
			if(Dizi[i]=='\n') {
				char[] copy_dizi=new char[i-sayac+1];
				int z=0;
				for(int j=sayac;j<i;j++) {
					copy_dizi[z]=Dizi[j];
					z++;
				}
				fis_verisi.add(new String(copy_dizi));
				sayac=i+1;
			
				continue;
			}
			
			
		}// gelen veriyi arrayliste atadik
		veri_tabani Veritabani=new veri_tabani();
		ArrayList<company> company_list=Veritabani.Company_list();
		// Firma Adý,Tarih,Saat,Fiþ No
		
		for(int i=0;i<fis_verisi.size();i++) {
			
			if(bulunan[0]!=1) {
			for(company j:company_list) {
				if(fis_verisi.get(i).toLowerCase().contains(j.getSearch_text().toLowerCase())) {
					this.company_name=j.getName();
					this.company_id=j.getId();
					bulunan[0]=1;
					bulunan_i[0]=i;
					break;
				}
			}
			}// Maðza isim bulma sonu
			
			if(bulunan[0]==1) {
				
				char satir[]=fis_verisi.get(i).toCharArray();
				if(bulunan_i[1]==-1) {
				Fis_Tarih_Bul(satir,i);
				}
				if(bulunan_i[2]==-1) {
				Fis_Saat_Bul(satir,i);
				}
				if(bulunan_i[2]==-1) {
				Fis_Saat_Bul2(satir,i);
				}
				if(bulunan_i[3]==-1) {
				Fis_No_Bul(satir,i);
				}
				if(bulunan_i[0]!=-1 && bulunan_i[1]!=-1 && bulunan_i[2]!=-1 && bulunan_i[3]!=-1 )
					break;
			
			}
		}
			
		Fis_UrunlerKdvToplam_Bul(fis_verisi);
		
		
		
			/*System.out.println(bulunan_i[0] +"  "+ bulunan_i[1]+"  "+ bulunan_i[2] +"   "+ bulunan_i[3]);
			System.out.println("Deneme:"+deneme);
			System.out.println("Son indis:"+son_indis(bulunan_i));
			System.out.println("Firma ID:"+company_id);
			System.out.println("Firma Adi:"+company_name);
			System.out.println("Fiþ Tarihi:"+plug_date);
			System.out.println("Fiþ Saati:"+plug_time);
			System.out.println("Fiþ No:"+plug_no);
			System.out.println("Toplam Fiyat:"+total_price);
			System.out.println("Toplam KDV:"+total_KDV);
			System.out.println("Ürünler:");
			System.out.println("Ürün Adý       Kdv    Fiyat");
			for(product list:product_list) {
				System.out.println(list.getName()+"  %"+list.getKDV()+" "+list.getPrice() );
			}
			*/
		
			if(bulunan_i[0]==-1 || bulunan_i[1]==-1 || bulunan_i[2]==-1 || bulunan_i[3]==-1 || total_price==0 || product_list.isEmpty() ) {
			if(deneme==0) {
			RecognizeText recognize =new RecognizeText();
			String new_veri=recognize.Cevir3(url);
			deneme++;
			KIRP(new_veri, url);
			return;
			}else if(deneme==1){
			RecognizeText recognize =new RecognizeText();
			String new_veri=recognize.Cevir2(url);
			deneme++;
			KIRP(new_veri, url);
			return;
			}else if(deneme==2) {
			RecognizeText recognize =new RecognizeText();
			String new_veri=recognize.Cevir(url); 
			deneme++;
			KIRP(new_veri, url);
			return;
		}else if(deneme==3) {
			RecognizeText recognize =new RecognizeText();
			String new_veri=recognize.Cevir5(url); 
			deneme++;
			KIRP(new_veri, url);
			return;
		}
	}
		
       veri_tabani db=new veri_tabani();
        
      if(db.getPlug_ID(company_id, plug_no)==0) {  
    	  if(bulunan_i[0]==-1 && bulunan_i[1]==-1 && bulunan_i[2]==-1 && bulunan_i[3]==-1 ) {
    		  System.out.println("Fiþ Okunamadý..");
    	  }else {
  			if(plug_date==null) {
  				plug_date="";
  						}
  			if(plug_time==null) {
  			  plug_time="";
  			}
        if(db.plug_add(company_id, plug_date, plug_time, plug_no,total_KDV,total_price)) {
        	System.out.println("Fiþ bilgileri kaydedildi.");
        	this.plug_id=db.getPlug_ID(company_id, plug_no);
        	int say=0,urun_sayisi=product_list.size();
        	for(product list:product_list) {
        		if(db.product_add(plug_id, list.getName(), list.getKDV(), list.getPrice())) {
        		say++;
        		}
        	}
        	if(say==urun_sayisi) {
        		System.out.println("Ürünler eklendi");
        	}
        	
        }else {
        	System.out.println("Fiþ bilgileri kaydedilmedi");
        }
    
    	  }	  
    }else {
    	  System.out.println("Bu Fiþ zaten var!!");
      } 
        System.out.println("["+bulunan_i[0]+"]"+"["+bulunan_i[1]+"]"+"["+bulunan_i[2]+"]"+"["+bulunan_i[3]+"]");
		System.out.println("Son indis:"+son_indis(bulunan_i));
		System.out.println("Firma ID:"+company_id);
		System.out.println("Firma Adi:"+company_name);
		System.out.println("Fiþ Tarihi:"+plug_date);
		System.out.println("Fiþ Saati:"+plug_time);
		System.out.println("Fiþ No:"+plug_no);
		System.out.println("Toplam Fiyat:"+total_price);
		System.out.println("Toplam KDV:"+total_KDV);
		System.out.println("Ürünler:");
		System.out.println("Ürün Adý       Kdv    Fiyat");
		for(product list:product_list) {
			System.out.println(list.getName()+"  %"+list.getKDV()+" "+list.getPrice() );
		}
	}
}
