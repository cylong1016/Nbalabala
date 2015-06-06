package data.teamdata;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

//import org.apache.batik.transcoder.Transcoder;
//import org.apache.batik.transcoder.TranscoderException;
//import org.apache.batik.transcoder.TranscoderInput;
//import org.apache.batik.transcoder.TranscoderOutput;
//import org.apache.batik.transcoder.image.PNGTranscoder;

import utility.Constants;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月20日  下午6:59:33
 */
public class TeamLogoCache {
	
	private static HashMap<String, Image> logos = new HashMap<String, Image>();
	
	/** 先检查temp文件夹下有无转换好的png，如果没有，转换之 */
    public static Image getTeamLogo(String abbr) {
    	if (abbr.equals("NOH")) abbr = "NOP";
    	else if (abbr.equals("NJN")) abbr = "BKN";
    	
    	Image result = logos.get(abbr);
    	if (result != null) {
    		return result;
    	}
    	
    	File file = new File("temp/" + abbr + ".png");
    	try {
    		logos.put(abbr, ImageIO.read(file));
			return logos.get(abbr);
		} catch (Exception e) {
			try {
				File dirFile = new File("temp/");
				if (!dirFile.exists()) dirFile.mkdirs();
				convertSvgFile2Png(new File(Constants.dataSourcePath + "teams/" + abbr + ".svg"), new File("temp/" + abbr +".png"));
				logos.put(abbr, ImageIO.read(new File("temp/" + abbr + ".png")));
				return logos.get(abbr);
			} catch (Exception e2){
				try {
					return ImageIO.read(new File("images/nullTeam.png"));
				} catch (IOException e1) {
					return null;
				}
			}
		}
    }
    
    public void loadLogos() {
    	new CacheThread().start();
    }
    
	//svg转为png  
    private static void convertSvgFile2Png(File svg, File pdf) throws IOException,TranscoderException   
    {  
        InputStream in = new FileInputStream(svg);  
        OutputStream out = new FileOutputStream(pdf);  
        out = new BufferedOutputStream(out);  
        convert2PNG(in, out);  
    }
    
    private static void convert2PNG(InputStream in, OutputStream out)throws IOException, TranscoderException  
    {  
        Transcoder transcoder = new PNGTranscoder();  
        try {  
            TranscoderInput input = new TranscoderInput(in);  
            try {  
                TranscoderOutput output = new TranscoderOutput(out);  
                transcoder.transcode(input, output);  
            } finally {  
                out.close();  
            }  
        } finally {  
            in.close();  
        }
    }
    
	private class CacheThread extends Thread{
		public void start() {
			File file = new File("temp/");
			if (!file.exists()) {
				file.mkdirs();
			}
			File [] files = file.listFiles();
			for (File imgFile : files) {
				String fileName = imgFile.getName();
				try {
					logos.put(fileName.substring(0, 3), ImageIO.read(imgFile));
				} catch (IOException e) {
					continue;
				}
			}
		}
	}
    
}
