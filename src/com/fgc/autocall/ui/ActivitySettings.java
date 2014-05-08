package com.fgc.autocall.ui;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.fgc.autocall.R;
import com.fgc.autocall.Tools.StringTools;
import com.fgc.autocall.Tools.Tools;
import com.fgc.autocall.ui.component.ButtonTwoState;
import com.fgc.autocall.ui.component.ButtonTwoState.OnTwoStateSwitchListener;

public class ActivitySettings extends BaseActivity{
	private static final String LOG_TAG = "ActivitySettings";
	
	private RelativeLayout mLayoutTitleBar;
	
	
	private EditText mEditInternal;
	private EditText mEditStartTime;
	private EditText mEditSimTimeLenght;
	private EditText mEditWarnningNumber;
	private ButtonTwoState mBtnInternal;
	private ButtonTwoState mBtnStartTime;
	private ButtonTwoState mBtnSimTimeLenght;
	private ButtonTwoState mBtnWarnningNumber;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.a_settings);
		
		initView();
	}

	private void initView()
	{
		mLayoutTitleBar = (RelativeLayout)findViewById(R.id.title_bar);
		Button btnLeft = (Button)mLayoutTitleBar.findViewById(R.id.title_btn_left);
		btnLeft.setOnClickListener(mOnClickListener);
		((ButtonTwoState)mLayoutTitleBar.findViewById(R.id.title_btn_right)).setVisibility(View.GONE);
		
		mEditInternal = (EditText)findViewById(R.id.input_call_internal);
		mEditInternal.setEnabled(false);
//		mEditInternal.setBackgroundResource(R.drawable.input_box_bg_g);
		mEditStartTime = (EditText)findViewById(R.id.input_start_time);
		mEditStartTime.setEnabled(false);
//		mEditStartTime.setBackgroundResource(R.drawable.input_box_bg_g);
		mEditSimTimeLenght = (EditText)findViewById(R.id.input_sim_time_length);
		mEditSimTimeLenght.setEnabled(false);
//		mEditSimTimeLenght.setBackgroundResource(R.drawable.input_box_bg_g);
		mEditWarnningNumber = (EditText)findViewById(R.id.input_warnning_number);
		mEditWarnningNumber.setEnabled(false);
//		mEditWarnningNumber.setBackgroundResource(R.drawable.input_box_bg_g);
		
		mBtnInternal = (ButtonTwoState)findViewById(R.id.btn_call_internal);
		mBtnInternal.setTwoStateDrawble(R.drawable.btn_edit, R.drawable.btn_ok);
		mBtnInternal.setOnTwoStateSwitchListener(mOnStatePauseSwitchListener);
		mBtnStartTime = (ButtonTwoState)findViewById(R.id.btn_start_time);
		mBtnStartTime.setTwoStateDrawble(R.drawable.btn_edit, R.drawable.btn_ok);
		mBtnStartTime.setOnTwoStateSwitchListener(mOnStatePauseSwitchListener);
		mBtnSimTimeLenght = (ButtonTwoState)findViewById(R.id.btn_sim_time_length);
		mBtnSimTimeLenght.setTwoStateDrawble(R.drawable.btn_edit, R.drawable.btn_ok);
		mBtnSimTimeLenght.setOnTwoStateSwitchListener(mOnStatePauseSwitchListener);
		mBtnWarnningNumber = (ButtonTwoState)findViewById(R.id.btn_warnning_number);
		mBtnWarnningNumber.setTwoStateDrawble(R.drawable.btn_edit, R.drawable.btn_ok);
		mBtnWarnningNumber.setOnTwoStateSwitchListener(mOnStatePauseSwitchListener);
	}
	
	private OnTwoStateSwitchListener mOnStatePauseSwitchListener = new OnTwoStateSwitchListener()
	{

		@Override
		public void onSwitch(boolean isPositive, int btnId) {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "onSwitch: " + isPositive);
			
			switch (btnId)
			{
			case R.id.btn_call_internal:
				Log.i(LOG_TAG, "click btn_call_internal");
				if (isPositive)
				{
					mEditInternal.setEnabled(false);
//					mEditInternal.setBackgroundResource(R.drawable.input_box_bg_g);
					String value = mEditInternal.getText().toString();
					if (StringTools.isPureNumber(value))
					{
						mApp.getConfigManager().saveCallInternal(Integer.parseInt(value));
					}
					else
					{
						Tools.ToastShow(ActivitySettings.this, "请输入正确的时长", false);
					}
				}
				else
				{
					mEditInternal.setEnabled(true);
//					mEditInternal.setBackgroundResource(R.drawable.input_box_bg);
					mEditInternal.setFocusable(true);
					mEditInternal.setFocusableInTouchMode(true);
				}
				break;
			case R.id.btn_start_time:
				Log.i(LOG_TAG, "click btn_start_time");
				if (isPositive)
				{
					mEditStartTime.setEnabled(false);
//					mEditStartTime.setBackgroundResource(R.drawable.input_box_bg_g);
					String value = mEditStartTime.getText().toString();
					if (StringTools.isTimeFormat(value))
					{
						mApp.getConfigManager().saveStartCallTime(value);
					}
					else
					{
						Tools.ToastShow(ActivitySettings.this, "请输入正确的时间", false);
					}
				}
				else
				{
					mEditStartTime.setEnabled(true);
//					mEditStartTime.setBackgroundResource(R.drawable.input_box_bg);
				}
				break;
			case R.id.btn_sim_time_length:
				Log.i(LOG_TAG, "click btn_sim_time_length");
				if (isPositive)
				{
					mEditSimTimeLenght.setEnabled(false);
//					mEditSimTimeLenght.setBackgroundResource(R.drawable.input_box_bg_g);
					String value = mEditSimTimeLenght.getText().toString();
					if (StringTools.isPureNumber(value))
					{
						mApp.getConfigManager().saveSimCardTimeLength(Integer.parseInt(value));
					}
					else
					{
						Tools.ToastShow(ActivitySettings.this, "请输入正确的时长", false);
					}
				}
				else
				{
					mEditSimTimeLenght.setEnabled(true);
//					mEditSimTimeLenght.setBackgroundResource(R.drawable.input_box_bg);
				}
				break;
			case R.id.btn_warnning_number:
				Log.i(LOG_TAG, "click btn_warnning_number");
				if (isPositive)
				{
					mEditWarnningNumber.setEnabled(false);
//					mEditWarnningNumber.setBackgroundResource(R.drawable.input_box_bg_g);
					String value = mEditWarnningNumber.getText().toString();
					if (StringTools.isMobile(value))
					{
						mApp.getConfigManager().saveWarnningPhoneNumber(value);
					}
					else
					{
						Tools.ToastShow(ActivitySettings.this, "请输入正确的手机号码", false);
					}
				}
				else
				{
					mEditWarnningNumber.setEnabled(true);
//					mEditWarnningNumber.setBackgroundResource(R.drawable.input_box_bg);
				}
				break;
			}
		}
		
	};
	
	private void fillEditBoxByDefaultValue()
	{
		int internal = mApp.getConfigManager().getCallInternal();
		mEditInternal.setText(String.valueOf(internal));
		
		String startTime = mApp.getConfigManager().getStartCallTime();
		mEditStartTime.setText(startTime);
		Calendar calendar = Tools.getCalendarForHourAndMinus(startTime);
		if (calendar != null)
		{
			Calendar now = new GregorianCalendar();
			if (calendar.after(now))
			{
				Log.i(LOG_TAG, "start time is after now");
			}
			else
			{
				Log.i(LOG_TAG, "start time is before now");
			}
		}
		
		int simLength = mApp.getConfigManager().getSimCardTimeLength();
		mEditSimTimeLenght.setText(String.valueOf(simLength));
		
		String warnningNumber = mApp.getConfigManager().getWarnningPhoneNumber();
		mEditWarnningNumber.setText(warnningNumber);
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		Log.i(LOG_TAG, "onStart");
	}
	
	@Override
	protected void onRestart()
	{
		super.onRestart();
		Log.i(LOG_TAG, "onRestart");
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(LOG_TAG, "onResume");
		fillEditBoxByDefaultValue();
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(LOG_TAG, "onPause");
	}

	@Override
	protected void onStop() {
		Log.i(LOG_TAG, "onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i(LOG_TAG, "onDestroy");
		super.onDestroy();
	}
	
	
	private OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.i(LOG_TAG, "onClick");
			
			switch (v.getId())
			{
			case R.id.title_btn_left:
				Log.i(LOG_TAG, "click title_btn_left");
				finish();
				break;
			}
		}
	};
}
