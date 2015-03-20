package data.teamdata;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月20日  下午6:59:33
 */
public class SVGHandler {
	
	/** 先检查temp文件夹下有无转换好的png，如果没有，转换之 */
    public static Image getTeamLogo(String abbr) {
    	File file = new File("temp/" + abbr + ".png");
    	try {
			return ImageIO.read(file);
		} catch (Exception e) {
			try {
				convertSvgFile2Png(new File("NBAdata/teams/" + abbr + ".svg"), new File("temp/" + abbr +".png"));
				return ImageIO.read(new File("temp/" + abbr + ".png"));
			} catch (Exception e2){
				try {
					return ImageIO.read(new File("images/nullTeam.png"));
				} catch (IOException e1) {
					return null;
				}
			}
		}
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
    
}
