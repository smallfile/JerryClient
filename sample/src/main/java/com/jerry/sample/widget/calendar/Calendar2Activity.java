package com.jerry.sample.widget.calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.uilib.widget.calendar2.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


public class Calendar2Activity extends Activity {
	private static final String TAG = "SampleTimesSquareActivity";
	private CalendarPickerView calendar;
	private AlertDialog theDialog;
	private CalendarPickerView dialogCalendar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar2);

		final Button single = (Button) findViewById(R.id.button_single);
		final Button multi = (Button) findViewById(R.id.button_multi);
		final Button range = (Button) findViewById(R.id.button_range);
		final Button displayOnly = (Button) findViewById(R.id.button_display_only);
		final Button dialog = (Button) findViewById(R.id.button_dialog);
		final Button customized = (Button) findViewById(R.id.button_customized);
		calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
		
		final Calendar startDate = Calendar.getInstance();
		startDate.add(Calendar.MONTH, -2);
		startDate.set(Calendar.DAY_OF_MONTH, 1);
		
		final Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.MONTH, 9);
		endDate.set(Calendar.DAY_OF_MONTH, endDate.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
		
		calendar.init(startDate.getTime(), endDate.getTime()) //
				.inMode(CalendarPickerView.SelectionMode.SINGLE) //
				.withSelectedDate(new Date());

		
		single.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				single.setEnabled(false);
				multi.setEnabled(true);
				range.setEnabled(true);
				displayOnly.setEnabled(true);

				calendar.init(startDate.getTime(), endDate.getTime()) //
						.inMode(CalendarPickerView.SelectionMode.SINGLE) //
						.withSelectedDate(new Date());
			}
		});

		multi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				single.setEnabled(true);
				multi.setEnabled(false);
				range.setEnabled(true);
				displayOnly.setEnabled(true);

				Calendar today = Calendar.getInstance();
				ArrayList<Date> dates = new ArrayList<Date>();
				for (int i = 0; i < 5; i++) {
					today.add(Calendar.DAY_OF_MONTH, 3);
					dates.add(today.getTime());
				}
				calendar.init(new Date(), endDate.getTime()) //
						.inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
						.withSelectedDates(dates);
			}
		});

		range.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				single.setEnabled(true);
				multi.setEnabled(true);
				range.setEnabled(false);
				displayOnly.setEnabled(true);

				Calendar today = Calendar.getInstance();
				ArrayList<Date> dates = new ArrayList<Date>();
				today.add(Calendar.DATE, 3);
				dates.add(today.getTime());
				today.add(Calendar.DATE, 5);
				dates.add(today.getTime());
				calendar.init(new Date(), endDate.getTime()) //
						.inMode(CalendarPickerView.SelectionMode.RANGE) //
						.withSelectedDates(dates);
			}
		});

		displayOnly.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				single.setEnabled(true);
				multi.setEnabled(true);
				range.setEnabled(true);
				displayOnly.setEnabled(false);

				calendar.init(new Date(), endDate.getTime()) //
						.inMode(CalendarPickerView.SelectionMode.SINGLE) //
						.withSelectedDate(new Date()).displayOnly();
			}
		});

		dialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				dialogCalendar = (CalendarPickerView) getLayoutInflater().inflate(
						R.layout.calendar_dialog, null, false);
				dialogCalendar.init(startDate.getTime(), endDate.getTime()) //
						.withSelectedDate(new Date());
				theDialog = new AlertDialog.Builder(Calendar2Activity.this)
						.setTitle("I'm a dialog!")
						.setView(dialogCalendar)
						.setNeutralButton("Dismiss",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialogInterface,
											int i) {
										dialogInterface.dismiss();
									}
								}).create();
				theDialog
						.setOnShowListener(new DialogInterface.OnShowListener() {
							@Override
							public void onShow(DialogInterface dialogInterface) {
								dialogCalendar.fixDialogDimens();
							}
						});
				theDialog.show();
			}
		});

		customized.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				dialogCalendar = (CalendarPickerView) getLayoutInflater() //
						.inflate(R.layout.calendar_dialog_customized, null,
								false);
				List<Date> dates = new ArrayList<Date>();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					dates.add(format.parse("2014-09-25"));
					dates.add(format.parse("2014-09-26"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				dialogCalendar.init(startDate.getTime(), endDate.getTime())
						.inMode(CalendarPickerView.SelectionMode.MULTIPLE)
						.withSelectedDates(dates);
				theDialog = new AlertDialog.Builder(Calendar2Activity.this)
						.setTitle("Pimp my calendar !")
						.setView(dialogCalendar)
						.setNeutralButton("Dismiss",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(
											DialogInterface dialogInterface,
											int i) {
										dialogInterface.dismiss();
									}
								})
								.setPositiveButton("确定", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialogInterface,
											int i) {
										List<Date> list = dialogCalendar.getSelectedDates();
										StringBuffer sb = new StringBuffer();
										for (Date date : list) {
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
											sb.append(sdf.format(date));
											sb.append("   ");
										}
										System.out.println("SelectedDates="+sb.toString());
										Toast.makeText(Calendar2Activity.this, sb.toString(),
												LENGTH_SHORT).show();
									}
								})
								.create();
				theDialog
						.setOnShowListener(new DialogInterface.OnShowListener() {
							@Override
							public void onShow(DialogInterface dialogInterface) {
								dialogCalendar.fixDialogDimens();
							}
						});
				theDialog.show();
			}
		});

		findViewById(R.id.done_button).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View view) {
						String toast = "Selected: "
								+ calendar.getSelectedDate().getTime();
						Toast.makeText(Calendar2Activity.this, toast,
								LENGTH_SHORT).show();
					}
				});
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		boolean applyFixes = theDialog != null && theDialog.isShowing();
		if (applyFixes) {
			dialogCalendar.unfixDialogDimens();
		}
		super.onConfigurationChanged(newConfig);
		if (applyFixes) {
			dialogCalendar.post(new Runnable() {
				@Override
				public void run() {
					dialogCalendar.fixDialogDimens();
				}
			});
		}
	}
}
