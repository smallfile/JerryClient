package com.jerry.sample.utils;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.jerry.sample.R;


/**
 * Contains several Dialog utilities.
 * @author Jerry Wang
 */
public final class DialogUtils {

	public final Context mContext;
	/**
	 * A string resource to use for the "Error" string message. Set this in the
	 * {@link Application#onCreate()}.
	 */
	public static int resError = -1;

	public DialogUtils(final Context context) {
		this.mContext = context;
	}

	/**
	 * Shows a simple yes/no dialog. The dialog does nothing and simply
	 * disappears when No is clicked.
	 * 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showYesNoDialog(final String message,
			final DialogInterface.OnClickListener yesListener) {
		showYesNoDialog(null, message, yesListener);
	}

	/**
	 * Shows a simple yes/no dialog. The dialog does nothing and simply
	 * disappears when No is clicked.
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showYesNoDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener) {
		showYesNoDialog(title,message,yesListener,null);
	}
	
	/**
	 * Shows a simple yes/no dialog. The dialog does nothing and simply
	 * disappears when No is clicked.
	 * 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showYesNoDialog(final String message,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		showYesNoDialog(null, message, yesListener,noListener);
	}

	/**
	 * Shows a simple yes/no dialog. The dialog does nothing and simply
	 * disappears when No is clicked.
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param yesListener
	 *            invoked when the Yes button is pressed.
	 */
	public void showYesNoDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		createYesNoDialog(title,message,yesListener,noListener).show();
	}
	
	/**
	 * Create a simple yes/no dialog. The dialog does nothing and simply
	 * disappears when No is clicked.
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param yesListener
	 *            invoked when the Yes button is pressed.
	 */
	public Dialog createYesNoDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton(R.string.common_yes, yesListener);
		if (noListener != null) {
			builder.setNegativeButton(R.string.common_no, noListener);
		} else {
			builder.setNegativeButton(R.string.common_no,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
		}
		
		return builder.create();
	}
	
	/**
	 * Shows a simple yes dialog. The dialog does nothing and simply
	 * disappears when No is clicked.
	 * 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showYesDialog(final String message,
			final DialogInterface.OnClickListener yesListener) {
		showYesDialog(null, message, yesListener);
	}

	/**
	 * Shows a simple yes dialog. The dialog does nothing and simply
	 * disappears when No is clicked.
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showYesDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener) {
		createYesDialog(title,message,yesListener).show();
	}
	
	/**
	 * Create a simple yes dialog. The dialog does nothing and simply
	 * disappears when No is clicked.
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public Dialog createYesDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton(R.string.common_yes, yesListener);
		return builder.create();
	}
	
	/**
	 * Shows a simple no dialog. 
	 * 
	 * @param message
	 *            the message to show
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showNoDialog(final String message,
			final DialogInterface.OnClickListener noListener) {
		showNoDialog(null, message, noListener);
	}

	/**
	 * Shows a simple no dialog. 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showNoDialog(final String title, final String message,
			final DialogInterface.OnClickListener noListener) {
		createNoDialog(title,message,noListener).show();
	}
	
	/**
	 * Shows a simple no dialog. 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public Dialog createNoDialog(final String title, final String message,
			final DialogInterface.OnClickListener noListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setNegativeButton(R.string.common_no, noListener);
		return builder.create();
	}
	
	/**
	 * Shows a simple ok/cancel dialog. 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showOkCancelDialog(final String message,
			final DialogInterface.OnClickListener yesListener) {
		showOkCancelDialog(null, message, yesListener);
	}

	/**
	 * Shows a simple ok/cancel dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showOkCancelDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener) {
		showOkCancelDialog(title,message,yesListener,null);
	}
	
	/**
	 * Shows a simple ok/cancel dialog. 
	 * 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showOkCancelDialog(final String message,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		showOkCancelDialog(null, message, yesListener,noListener);
	}

	/**
	 * Shows a simple ok/cancel dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param yesListener
	 *            invoked when the Yes button is pressed.
	 */
	public void showOkCancelDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		createOkCancelDialog(title,message,yesListener,noListener).show();
	}
	
	/**
	 * Shows a simple ok/cancel dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param yesListener
	 *            invoked when the Yes button is pressed.
	 */
	public Dialog createOkCancelDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton(R.string.common_ok, yesListener);
		if (noListener != null) {
			builder.setNegativeButton(R.string.common_cancel, noListener);
		} else {
			builder.setNegativeButton(R.string.common_cancel,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
		}
		return builder.create();
	}
	
	/**
	 * Shows a simple ok dialog. 
	 * 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showOkDialog(final String message,
			final DialogInterface.OnClickListener yesListener) {
		showOkDialog(null, message, yesListener);
	}

	/**
	 * Shows a simple ok dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showOkDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener) {
		createOkDialog(title,message,yesListener).show();
	}
	
	/**
	 * Create a simple ok dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public Dialog createOkDialog(final String title, final String message,
			final DialogInterface.OnClickListener yesListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton(R.string.common_ok, yesListener);
		return builder.create();
	}
	
	/**
	 * Shows a simple cancel dialog. 
	 * 
	 * @param message
	 *            the message to show
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showCancelDialog(final String message,
			final DialogInterface.OnClickListener noListener) {
		showCancelDialog(null, message, noListener);
	}

	/**
	 * Shows a simple cancel dialog. 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showCancelDialog(final String title, final String message,
			final DialogInterface.OnClickListener noListener) {
		createCancelDialog(title,message,noListener).show();
	}
	
	/**
	 * Create a simple cancel dialog. 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public Dialog createCancelDialog(final String title, final String message,
			final DialogInterface.OnClickListener noListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setNegativeButton(R.string.common_cancel, noListener);
		return builder.create();
	}
	
	/**
	 * Shows a simple custom yes/ no dialog. 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showCustYesNoDialog(final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener) {
		showCustYesNoDialog(null, message, custYes, yesListener);
	}

	/**
	 * Shows a simple custom yes/no dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showCustYesNoDialog(final String title, final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener) {
		createCustYesNoDialog(title,message,custYes,yesListener).show();
	}

	/**
	 * Shows a simple custom yes/no dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public Dialog createCustYesNoDialog(final String title, final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton(custYes, yesListener);
		builder.setNegativeButton(R.string.common_no,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		return builder.create();
	}
	
	/**
	 * Shows a simple custom yes/ cancel dialog. 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showCustYesCancelDialog(final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener) {
		showCustYesCancelDialog(null, message, custYes, yesListener);
	}

	/**
	 * Shows a simple custom yes/no dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showCustYesCancelDialog(final String title, final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener) {
		createCustYesCancelDialog(title,message,custYes,yesListener).show();
	}
	
	/**
	 * Shows a simple custom yes/no dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public Dialog createCustYesCancelDialog(final String title, final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton(custYes, yesListener);
		builder.setNegativeButton(R.string.common_cancel,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		return builder.create();
	}
	
	/**
	 * Shows a simple custom yes/custom no dialog. 
	 * 
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showCustYesCustNoDialog(final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener,
			final String custNo, final DialogInterface.OnClickListener noListener) {
		showCustYesCustNoDialog(null, message, custYes, yesListener, custNo, noListener);
	}

	/**
	 * Shows a simple custom yes/custom no dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param yesListener
	 *            invoked when the Yes button is pressed.
	 */
	public void showCustYesCustNoDialog(final String title, final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener,
			final String custNo, final DialogInterface.OnClickListener noListener) {
		createCustYesCustNoDialog(title,message,custYes,yesListener,custNo,noListener).show();
	}
	
	/**
	 * Create a simple custom yes/custom no dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param message
	 *            the message to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param yesListener
	 *            invoked when the Yes button is pressed.
	 */
	public Dialog createCustYesCustNoDialog(final String title, final String message,
			final String custYes, final DialogInterface.OnClickListener yesListener,
			final String custNo, final DialogInterface.OnClickListener noListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setCancelable(false);
		builder.setPositiveButton(custYes, yesListener);
		builder.setNegativeButton(custNo, noListener);
		return builder.create();
	}
	
	/**
	 * Shows a simple SingleChoiceItems dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog
	 * @param items
	 *            the res to show
	 * @param checkedItem
	 *            the position of checked
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showSingleChoiceItemsDialog(final String title, 
			final CharSequence[] items, final int checkedItem,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		createSingleChoiceItemsDialog(title,items, checkedItem,yesListener,noListener).show();
	}
	
	/**
	 * Shows a simple SingleChoiceItems dialog. 
	 * 
	 * @param title
	 * 			  an optional title of the dialog
	 * @param itemsId
	 *            the resId to show
	 * @param checkedItem
	 *            the position of checked
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showSingleChoiceItemsDialog(final String title, 
			final int itemsId, final int checkedItem,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		String[] values = mContext.getResources().getStringArray(itemsId);
		createSingleChoiceItemsDialog(title,values, checkedItem,yesListener,noListener).show();
	}
	
	/**
	 * Create a simple SingleChoiceItems dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
     * @param items
	 *            the res to show
	 * @param checkedItem
	 *            the position of checked
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param noListener
	 *            invoked when the Yes button is pressed.
	 */
	public Dialog createSingleChoiceItemsDialog(final String title, 
			final CharSequence[] items, final int checkedItem,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setSingleChoiceItems(items,checkedItem,yesListener);
		builder.setNegativeButton(R.string.common_cancel,noListener);
		return builder.create();
	}
	
	/**
	 * Shows a simple Items dialog. 
	 * 
	 * @param Item
	 *            the item to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showItemsDialog(final CharSequence[] items,
			final DialogInterface.OnClickListener yesListener) {
		createItemsDialog(items,yesListener).show();
	}
	
	/**
	 * Shows a simple Items dialog. 
	 * 
	 * @param Item
	 *            the item to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 */
	public Dialog createItemsDialog(
			final CharSequence[] items,
			final DialogInterface.OnClickListener yesListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setItems(items,yesListener);
		return builder.create();
	}
	
	/**
	 * Shows a simple Custom View dialog. 
	 * 
	 * @param title
	 * 			  an optional title of the dialog
	 * @param view
	 *            the view to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param noListener
	 *            invoked when the Yes button is pressed. 
	 */
	public void showCustViewDialog(final String title, final View view,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		createCustViewDialog(title, view, yesListener,noListener).show();
	}
	
	/**
	 * Create a simple Custom View dialog. 
	 * 
	 * @param title
	 *            an optional title of the dialog. When null or blank the title
	 *            will not be shown.
	 * @param view
	 *            the view to show
	 * @param yesListener
	 *            invoked when the Yes button is pressed. 
	 * @param noListener
	 *            invoked when the Yes button is pressed.
	 */
	public Dialog createCustViewDialog(final String title, final View view,
			final DialogInterface.OnClickListener yesListener,
			final DialogInterface.OnClickListener noListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setView(view);
		builder.setPositiveButton(R.string.common_ok, yesListener);
		builder.setNegativeButton(R.string.common_cancel,noListener);
		return builder.create();
	}
	
	/**
	 * Returns a localized "Error" string if available.
	 * 
	 * @return localized error string if available.
	 */
	public String getErrorMsg() {
		return resError == -1 ? "Error" : mContext.getString(resError);
	}
	
	/**
	 * Shows an error dialog.
	 * 
	 * @param messageRes
	 *            the message to show.
	 */
	public void showErrorDialog(final int messageRes) {
		showErrorDialog(mContext.getString(messageRes));
	}

	/**
	 * Shows an error dialog.
	 * 
	 * @param message
	 *            the message to show.
	 */
	public void showErrorDialog(final String message) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		builder.setTitle(getErrorMsg());
		builder.setCancelable(false);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.create().show();
	}

	/**
	 * Shows an information dialog.
	 * 
	 * @param title
	 *            an optional title. If -1 then no title will be shown.
	 * @param message
	 *            the dialog message.
	 */
	public void showInfoDialog(final int title, final int message) {
		showInfoDialog(title == -1 ? null : mContext.getString(title),
				mContext.getString(message));
	}

	/**
	 * Shows an information dialog.
	 * 
	 * @param title
	 *            an optional title. If null then no title will be shown.
	 * @param message
	 *            the dialog message.
	 */
	public void showInfoDialog(final String title, final String message) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setIcon(R.mipmap.ic_dialog);
		builder.setMessage(message);
		if (title != null) {
			builder.setTitle(title);
		}
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.create().show();
	}
	
	/**
	 * Shows a quick info toast at the bottom of the screen.
	 * 
	 * @param messageRes
	 *            the message to show.
	 */
	public void showToast(final int messageRes) {
		showToast(mContext.getString(messageRes));
	}

	/**
	 * Shows a quick info toast.
	 * 
	 * @param message
	 *            the message to show.
	 */
	public void showToast(final String message) {
		final Toast toast = Toast.makeText(mContext, message,
				Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public ProgressDialog createProgressDialog(String msg, OnCancelListener listener) {
		ProgressDialog progressDialog = new ProgressDialog(mContext);
		progressDialog.setIcon(R.mipmap.ic_dialog);
		progressDialog.setTitle(R.string.app_name);
		progressDialog.setMessage(msg);
		progressDialog.setCancelable(true);
		progressDialog.setOnCancelListener(listener);
		return progressDialog;
	}
	
}