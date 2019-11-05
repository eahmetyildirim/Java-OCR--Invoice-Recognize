package eclipse_deneme1;

public class plug {
private int id;
private int company_id;
private String company_name;
private String date;
private String time;
private int plug_no;
private double total_kdv;
private double total_price;


public plug(int id, int company_id, String date, String time, int plug_no, double total_kdv, double total_price) {
	super();
	this.id = id;
	this.company_id = company_id;
	this.date = date;
	this.time = time;
	this.plug_no = plug_no;
	this.total_kdv = total_kdv;
	this.total_price = total_price;
}

public plug(int id, int company_id, String company_name, String date, String time, int plug_no, double total_kdv,
		double total_price) {
	super();
	this.id = id;
	this.company_id = company_id;
	this.company_name = company_name;
	this.date = date;
	this.time = time;
	this.plug_no = plug_no;
	this.total_kdv = total_kdv;
	this.total_price = total_price;
}

public String getCompany_name() {
	return company_name;
}

public void setCompany_name(String company_name) {
	this.company_name = company_name;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCompany_id() {
	return company_id;
}
public void setCompany_id(int company_id) {
	this.company_id = company_id;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getPlug_no() {
	return plug_no;
}
public void setPlug_no(int plug_no) {
	this.plug_no = plug_no;
}
public double getTotal_kdv() {
	return total_kdv;
}
public void setTotal_kdv(double total_kdv) {
	this.total_kdv = total_kdv;
}
public double getTotal_price() {
	return total_price;
}
public void setTotal_price(double total_price) {
	this.total_price = total_price;
}


}
