package zltd.com.testwork.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Method;

import static android.R.attr.text;
import static android.R.id.text2;

public class BluetoothUtils {

	private static BluetoothUtils mBluetoothUtils;
	private BluetoothAdapter mBluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
	private  Activity activity;

	public static BluetoothUtils getInstance() {
		if (mBluetoothUtils == null) {
			mBluetoothUtils = new BluetoothUtils();
		}
		return mBluetoothUtils;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public  void  openBluetoothDevice(){
		if(!mBluetoothAdapter.isEnabled()){
//			mBluetoothAdapter.enable();
			Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			// 设置蓝牙可见性，最多300秒
			intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			activity.startActivity(intent);
		}
	}

	public void registerBluetoothBoardCast(){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
		intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
		intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
		intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
// 注册广播接收器，接收并处理搜索结果
		activity.registerReceiver(mReceiver, intentFilter);
// 寻找蓝牙设备，android会将查找到的设备以广播形式发出去
		mBluetoothAdapter.startDiscovery();
	}

	public void unRegisterBluetoothBoardCast(){
		activity.unregisterReceiver(mReceiver);
	}

    public  void searchBluetoothDevice(){

	}

	//定义广播接收
	private BroadcastReceiver mReceiver=new BroadcastReceiver(){



		@Override
		public void onReceive(Context context, Intent intent) {

			String action=intent.getAction();

			Log.i("debug", action);
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//				// 获取查找到的蓝牙设备
//				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//				System.out.println(device.getName());
//				// 如果查找到的设备符合要连接的设备，处理
//				if (device.getName().equalsIgnoreCase(name)) {
//					// 搜索蓝牙设备的过程占用资源比较多，一旦找到需要连接的设备后需要及时关闭搜索
//					adapter.cancelDiscovery();
//					// 获取蓝牙设备的连接状态
//					connectState = device.getBondState();
//					switch (connectState) {
//						// 未配对
//						case BluetoothDevice.BOND_NONE:
//							// 配对
//							try {
//								Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
//								createBondMethod.invoke(device);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//							break;
//						// 已配对
//						case BluetoothDevice.BOND_BONDED:
//							try {
//								// 连接
//								connect(device);
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//							break;
//					}
//				}
//			} else if(BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
//				// 状态改变的广播
//				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//				if (device.getName().equalsIgnoreCase(name)) {
//					connectState = device.getBondState();
//					switch (connectState) {
//						case BluetoothDevice.BOND_NONE:
//							break;
//						case BluetoothDevice.BOND_BONDING:
//							break;
//						case BluetoothDevice.BOND_BONDED:
//							try {
//								// 连接
//								connect(device);
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//							break;
//					}
//				}
			}
		}

	};

}