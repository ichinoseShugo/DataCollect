package cmx.crestmuse.jp.data_collection_kinect_v2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * Created by mizun on 2017/10/24.
 */
public class ddata_create {
    public static void main(String args[]) {
        try{
            /*
            //読み込み先の設定
            FileReader fr = new FileReader("testdata.txt");
            BufferedReader br = new BufferedReader(fr);
            */
            //書き込み先の設定(加速度などの採取したデータ)
            /*
            FileWriter pw = new FileWriter("practicedata.txt");
            */
            //書き込み先の設定(コード進行などのデータ)
            FileWriter pw = new FileWriter("test2.txt");

            FileReader fr = new FileReader("kinect_data.csv");
            StreamTokenizer st = new StreamTokenizer(fr);



            double previousnumber = 0;
            double currentnumber = 0;
            /*変数n=lineNumber
            i = コードナンバーの1つめ
            j = コードナンバーの2つめ
            c = 入力するコード
            k = ピアノコードナンバーの1つめ
            l = ピアノコードナンバーの2つめ
            p = 入力するピアノコード
             */
            double numberbox[] = new double[5];
            st.nextToken();
            while(st.nextToken()!=StreamTokenizer.TT_EOF) {
                for (int x = 0; x < 4; x++)
                    st.nextToken();
                numberbox[0] = st.nval;


                for (int x = 0; x < 2; x++)
                    st.nextToken();

                currentnumber = numberbox[0] - previousnumber;
                pw.write(""+String.format("%.2f", currentnumber)+"\r\n");
                previousnumber = numberbox[0];

            }
            fr.close();
            pw.close();
        } catch(IOException e){

        }
    }
}
