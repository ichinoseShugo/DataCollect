package cmx.crestmuse.jp.data_collection_kinect_v2;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by korona on 2016/10/07.
 */
public class PrintClass {

    private PrintWriter sensorWiter;
    private PrintWriter opFlowWriter;

    public PrintClass() {
    }

    public void setFile(String subject_name){
        try {
            //ファイル名に指定するための実験日時を取得
            Date date = new Date();

            //ファイルを書き込むディレクトリの生成
            String directoryPath = Environment.getExternalStorageDirectory().getPath() + "/Android/data/trainingdata/"+date.toString()+"_"+subject_name+"/";
            new File(directoryPath).mkdir();

            //ファイルのパスを指定
            String sensorFilePath = directoryPath + "Sensor.csv";
            String opFlowFilePath = directoryPath + "OpFlow.csv";

            //書き込みファイルを生成
            sensorWiter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(sensorFilePath)), "SHIFT_JIS"));
            opFlowWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(opFlowFilePath)), "SHIFT_JIS"));

            sensorWiter.print("SensorTime," +
                    "position_X,position_Y,position_Z," +
                    "velocity_X,velocity_Y,velocity_Z,velocity_Change," +
                    "acceleration_X,acceleration_Y,acceleration_Z," +
                    "gyro_X,gyro_Y,gyro_Z," +
                    "rotation_X,rotation_Y,rotation_Z," +
                    "gravity_X,gravity_Y,gravity_Z," +
                    "Touch\r\n");
            opFlowWriter.print("OpFlowTime,opFlow_X,opFlow_Y\r\n");
        }catch(IOException e){
        }
    }

    public void writeSensor(Vector3f position, Vector3f vVec, float vc, Vector3f accVec,
                            float[] gyro, float[] rotation, Vector3f gravity, long secondTime, int tap) {
        //ファイルに記録
        sensorWiter.print(secondTime + ",");

        sensorWiter.print(String.format("%.2f", position.values[0]) + ",");
        sensorWiter.print(String.format("%.2f", position.values[1]) + ",");
        sensorWiter.print(String.format("%.2f", position.values[2]) + ",");

        sensorWiter.print(String.format("%.2f", vVec.values[0]) + ",");
        sensorWiter.print(String.format("%.2f", vVec.values[1]) + ",");
        sensorWiter.print(String.format("%.2f", vVec.values[2]) + ",");

        sensorWiter.print(String.format("%.2f", vc) + ",");

        sensorWiter.print(String.format("%.2f", accVec.values[0]) + ",");
        sensorWiter.print(String.format("%.2f", accVec.values[1]) + ",");
        sensorWiter.print(String.format("%.2f", accVec.values[2]) + ",");

        sensorWiter.print(String.format("%.2f", gyro[0]) + ",");
        sensorWiter.print(String.format("%.2f", gyro[1]) + ",");
        sensorWiter.print(String.format("%.2f", gyro[2]) + ",");

        sensorWiter.print(String.format("%.2f", rotation[0]) + ",");
        sensorWiter.print(String.format("%.2f", rotation[1]) + ",");
        sensorWiter.print(String.format("%.2f", rotation[2]) + ",");

        sensorWiter.print(String.format("%.2f", gravity.values[0]) + ",");
        sensorWiter.print(String.format("%.2f", gravity.values[1]) + ",");
        sensorWiter.print(String.format("%.2f", gravity.values[2]) + ",");

        sensorWiter.print(tap+"\r\n");
    }

    public void writeFlow(long secondTime, double opFlowX, double opFlowY){
        opFlowWriter.print(secondTime + ","+ String.format("%.2f", opFlowX) + "," + String.format("%.2f", opFlowY) + "\r\n");
    }

    public void closeWriter(long DelayTime1,long DelayTime2){
        sensorWiter.flush();
        sensorWiter.println("通信往復にかかった時間="+DelayTime1);
        sensorWiter.println("通信終了からタイマー開始までにかかった時間="+DelayTime2);
        sensorWiter.close();

        opFlowWriter.flush();
        opFlowWriter.println("通信往復にかかった時間="+DelayTime1);
        opFlowWriter.println("通信終了からタイマー開始までにかかった時間="+DelayTime2);
        opFlowWriter.close();
    }
    /*
    public void finalize(){
        out.close();
    }*/
}