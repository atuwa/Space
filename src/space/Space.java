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
import java.nio.charset.StandardCharsets;

public class Space{

	//完成
	//同じことするプログラムでも人によって書き方変わるから決まった答えはない
	//計算機だからねPC
	//Javaプログラムの書き方と言うかAPI・ライブラリの使い方みたいなところあるからね
	//とにかくシンプルしする方針で
	//拡張性とか捨てる
	public static void main(String[] args){
		String outpath="output.txt";//出力ファイル直書き
		String inpath="input.txt";//入力ファイル直書き
		File infile=new File(inpath);//パス文字列からFileインスタンス生成
		File outfile=new File(outpath);//出力版
		try{
			//ローカル変数の変数名は頭文字が使われることが多い
			//みんな考えるのがめんどくさいからね
			//正直わかりやすい変数名が付けれるならそっちのほうがいいけど
			FileInputStream fis=new FileInputStream(infile);//ファイルからバイナリを入力するストリームを生成
			//日本語変数も使えるけどコンパイル時に文字化けする環境もあるからあまりお勧めはしない
			//コメントは文字化けしても実行に影響しないからコメントはどんどん書こう
			//書いたときは何してるかわかってても忘れるから
			//書くの面倒くさいけど書こう(2回目)
			//文字コード指定はこの段階でする
			InputStreamReader isr=new InputStreamReader(fis,StandardCharsets.UTF_8);//バイナリ入力ストリームから文字入力ストリームを作成
			BufferedReader 文字入力ストリーム=new BufferedReader(isr);//1文字ずつ処理するならバッファリングした方がいい
			FileOutputStream fos=new FileOutputStream(outfile);//出力バイナリストリーム
			OutputStreamWriter osw=new OutputStreamWriter(fos,StandardCharsets.UTF_8);//出力文字ストリーム
			BufferedWriter 文字出力ストリーム=new BufferedWriter(osw);//バッファリング出力ストリーム
			try{
				//かっこの中は次にループするか
				//この場合は必ずループする
				while(true) {//いつまでループするかわからないときはwhileが使われる。forでも同じ事できるけど
					/*戻り値:0 - 65535 (0x00-0xffff)の範囲の整数としての、読み込まれた文字。ストリームの終わりに達した場合は -1
					 * とあるので終わったら-1が来るらしい
					 * */
					int 入力文字=文字入力ストリーム.read();
					if(入力文字<0)break;//-1が来たらループを終わる、つまりすべて読み込んだらループを終わる
					if(入力文字=='　') {//読み込んだ文字が全角スペースの時
						文字出力ストリーム.write(' ');//半角文字を書き込み
					}else {//それ以外
						文字出力ストリーム.write(入力文字);
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally {//finally節は必ず実行したい処理を書く
				//今回はファイルを閉じる処理
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
		}catch(FileNotFoundException e){//ファイルが無かったり開けない時はこの例外が発生する
			e.printStackTrace();//例外の内容を出力する
			//それの内容の読み方は知ってた方がいい
		}
	}
}
