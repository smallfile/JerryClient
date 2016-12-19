// Copyright 2013 Square, Inc.
package com.jerry.uilib.widget.calendar2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jerry.uilib.R;

public class CalendarCellView extends TextView {
  private static final int[] STATE_SELECTABLE = {
      R.attr.calendarSelectable
  };
  private static final int[] STATE_CURRENT_MONTH = {
      R.attr.calendarCurrentMonth
  };
  private static final int[] STATE_TODAY = {
      R.attr.calendarToday
  };
  private static final int[] STATE_HIGHLIGHTED = {
      R.attr.calendarHighlighted
  };
  private static final int[] STATE_RANGE_FIRST = {
      R.attr.calendarRangeFirst
  };
  private static final int[] STATE_RANGE_MIDDLE = {
      R.attr.calendarRangeMiddle
  };
  private static final int[] STATE_RANGE_LAST = {
      R.attr.calendarRangeLast
  };

  private boolean isSelectable = false;
  private boolean isCurrentMonth = false;
  private boolean isToday = false;
  private boolean isHighlighted = false;
  private MonthCellDescriptor.RangeState rangeState = MonthCellDescriptor.RangeState.NONE;

  @SuppressWarnings("UnusedDeclaration")
  public CalendarCellView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setSelectable(boolean isSelectable) {
    this.isSelectable = isSelectable;
    refreshDrawableState();
  }

  public void setCurrentMonth(boolean isCurrentMonth) {
    this.isCurrentMonth = isCurrentMonth;
    refreshDrawableState();
  }

  public void setToday(boolean isToday) {
    this.isToday = isToday;
    refreshDrawableState();
  }

  public void setRangeState(MonthCellDescriptor.RangeState rangeState) {
    this.rangeState = rangeState;
    refreshDrawableState();
  }

  public void setHighlighted(boolean highlighted) {
    isHighlighted = highlighted;
    refreshDrawableState();
  }

  @Override protected int[] onCreateDrawableState(int extraSpace) {
    final int[] drawableState = super.onCreateDrawableState(extraSpace + 5);

    if (isSelectable) {
      mergeDrawableStates(drawableState, STATE_SELECTABLE);
    }

    if (isCurrentMonth) {
      mergeDrawableStates(drawableState, STATE_CURRENT_MONTH);
    }

    if (isToday) {
      mergeDrawableStates(drawableState, STATE_TODAY);
    }

    if (isHighlighted) {
      mergeDrawableStates(drawableState, STATE_HIGHLIGHTED);
    }

    if (rangeState == MonthCellDescriptor.RangeState.FIRST) {
      mergeDrawableStates(drawableState, STATE_RANGE_FIRST);
    } else if (rangeState == MonthCellDescriptor.RangeState.MIDDLE) {
      mergeDrawableStates(drawableState, STATE_RANGE_MIDDLE);
    } else if (rangeState == MonthCellDescriptor.RangeState.LAST) {
      mergeDrawableStates(drawableState, STATE_RANGE_LAST);
    }

    return drawableState;
  }
}
