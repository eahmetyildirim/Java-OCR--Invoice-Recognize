package eclipse_deneme1;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;



public class RecognizeText {
  
	
	
	
  // Source path content images
  static String SRC_PATH = "D:/calismalarim/eclipse/";

  // Create tess obj
  static Tesseract tesseract = new Tesseract();
  
  // Load OPENCV
  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    tesseract.setDatapath("C:/Program Files (x86)/Tesseract-OCR/tessdata/");
    tesseract.setLanguage("tur");
    tesseract.setLanguage("deu");
  // tesseract.setLanguage("eng");
  }
  public Mat gri_yap(Mat source){
      Mat gri=new Mat();
        cvtColor(source, gri, COLOR_BGR2GRAY); 
      return gri;
      }
  public Mat keskinlestirme(Mat dst){
        Mat destination = new Mat(dst.rows(),dst.cols(),dst.type());
       Imgproc.GaussianBlur(dst, destination, new Size(0,0), 10);
       Core.addWeighted(dst, 1.5, destination, -0.5, 0, destination);
        
      return destination;
      }
  public Mat siyahSiyah_beyazBeyaz(Mat source,double deger){
      Mat cikti=new Mat();
             Imgproc.threshold(source, cikti, deger,255,Imgproc.THRESH_BINARY);
           
      return cikti;
      }
  
public  String Cevir(String url) {
	Mat inputMat = Imgcodecs.imread(url);
	String result = "";
    Mat gray =siyahSiyah_beyazBeyaz(gri_yap(keskinlestirme(inputMat)),200);
     
    Imgcodecs.imwrite("gray.png", gray);
    Imgcodecs.imwrite("cikti1.png", gray);
    try {
      // Recognize text with OCR
      result = tesseract.doOCR(new File("gray.png"));
    } catch (TesseractException e) {
      e.printStackTrace();
    }

    return result;
  }


public  String Cevir2(String url) {
	Mat inputMat = Imgcodecs.imread(url);
	String result = "";
    Mat gray =gri_yap(keskinlestirme(inputMat));
    Imgcodecs.imwrite("cikti2.png", gray);
    Imgcodecs.imwrite("gray.png", gray);

    try {
      // Recognize text with OCR
      result = tesseract.doOCR(new File("gray.png"));
    } catch (TesseractException e) {
      e.printStackTrace();
    }

    return result;
  }
public  String Cevir3(String url) {
	Mat inputMat = Imgcodecs.imread(url);
	String result = "";
    Mat gray =siyahSiyah_beyazBeyaz(gri_yap(keskinlestirme(inputMat)),100);
    Imgcodecs.imwrite("cikti3.png", gray);
    Imgcodecs.imwrite("gray.png", gray);

    try {
      // Recognize text with OCR
      result = tesseract.doOCR(new File("gray.png"));
    } catch (TesseractException e) {
      e.printStackTrace();
    }

    return result;
  }
public  String Cevir4(String url) {
	Mat inputMat = Imgcodecs.imread(url);
	String result = "";
    Mat gray =keskinlestirme(inputMat);
    Imgcodecs.imwrite("cikti4.png", gray);
    Imgcodecs.imwrite("gray.png", gray);

    try {
      // Recognize text with OCR
      result = tesseract.doOCR(new File("gray.png"));
    } catch (TesseractException e) {
      e.printStackTrace();
    }

    return result;
  }  
  
public  String Cevir5(String url) {
	Mat inputMat = Imgcodecs.imread(url);
	String result = "";
    Mat gray =siyahSiyah_beyazBeyaz(gri_yap(keskinlestirme(inputMat)),60);
    Imgcodecs.imwrite("cikti5.png", gray);
    Imgcodecs.imwrite("gray.png", gray);

    try {
      // Recognize text with OCR
      result = tesseract.doOCR(new File("gray.png"));
    } catch (TesseractException e) {
      e.printStackTrace();
    }

    return result;
  }

}