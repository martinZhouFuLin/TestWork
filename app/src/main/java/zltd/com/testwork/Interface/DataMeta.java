package zltd.com.testwork.Interface;

/**
 * Created by q on 2017/7/24.
 */

public interface DataMeta {
    public void addScanner(DataScanner mDataScanner);

    public void removeScanner(DataScanner mDataScanner);

    public void  scanData( String barcode);
}
