package eclipse_deneme1;

public class product {
private String name;
private int KDV;
private double price;
private int plug_id;
private int id;

public product() {
	
}


public product(String name, int kDV, double price, int plug_id, int id) {

	this.name = name;
	KDV = kDV;
	this.price = price;
	this.plug_id = plug_id;
	this.id = id;
}


public int getPlug_id() {
	return plug_id;
}


public void setPlug_id(int plug_id) {
	this.plug_id = plug_id;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public product(String name, int kdv, double price) {

	this.name = name;
	this.KDV = kdv;
	this.price = price;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getKDV() {
	return KDV;
}
public void setKDV(int kDV) {
	KDV = kDV;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}

}
