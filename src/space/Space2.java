package space;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class Space2 extends Writer{

	private BufferedWriter bw;
	public Space2(Writer osw){
		bw=new BufferedWriter(osw);
	}
	@Override
	public void write(char[] cbuf,int off,int len) throws IOException{
		for(char c:cbuf) {
			if(c=='　')c=' ';
			bw.write(c);
		}
	}
	@Override
	public void flush() throws IOException{
		bw.flush();
	}
	@Override
	public void close() throws IOException{
		bw.close();
	}
	//少し拡張性を増やしたバージョン
	public static void main(String[] args){
		String outpath="output.txt";
		String inpath="input.txt";
		File infile=new File(inpath);
		File outfile=new File(outpath);
		try{
			FileInputStream fis=new FileInputStream(infile);
			InputStreamReader isr=new InputStreamReader(fis,StandardCharsets.UTF_8);
			BufferedReader 文字入力ストリーム=new BufferedReader(isr);
			FileOutputStream fos=new FileOutputStream(outfile);
			OutputStreamWriter osw=new OutputStreamWriter(fos,StandardCharsets.UTF_8);
			Writer 文字出力ストリーム=new Space2(osw);
			try{
				char[] 文字入力バッファ配列=new char[1024];
				while(true) {
					int 入力文字数=文字入力ストリーム.read(文字入力バッファ配列);
					if(入力文字数<1)break;//1文字以下の時ループを終わる
					//文字出力ストリームに入力バッファ配列の0文字目から入力文字数まで書き出し
					文字出力ストリーム.write(文字入力バッファ配列,0,入力文字数);
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally {
				try{
					文字入力ストリーム.close();
				}catch(IOException e){
					e.printStackTrace();
				}
				try{
					文字出力ストリーム.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
