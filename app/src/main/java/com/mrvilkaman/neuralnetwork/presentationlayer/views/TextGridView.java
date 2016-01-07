package com.mrvilkaman.neuralnetwork.presentationlayer.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class TextGridView extends View {

	private int numColumns, numRows;
	private int cellWidth, cellHeight;
	private Paint blackPaint = new Paint();
	private int[][] cellChecked;


	public TextGridView(Context context) {
		super(context);
		init();
	}

	public TextGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TextGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		setNumColumns(10);
		setNumRows(10);
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
		calculateDimensions();
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
		calculateDimensions();
	}

	public int getNumRows() {
		return numRows;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		calculateDimensions();
	}

	private void calculateDimensions() {
		if (numColumns == 0 || numRows == 0)
			return;

		cellWidth = getWidth() / numColumns;
		cellHeight = getHeight() / numRows;

		cellChecked = new int[numColumns][numRows];
		invalidate();
	}

	public void setNewValues(int[][] cellChecked){
		this.cellChecked = cellChecked;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawColor(Color.WHITE);

		if (numColumns == 0 || numRows == 0)
			return;

		int width = getWidth();
		int height = getHeight();

		for (int i = 0; i < numColumns; i++) {
			for (int j = 0; j < numRows; j++) {

				int value = cellChecked[i][j];
				String text = String.valueOf(value);
				float padding = blackPaint.measureText(text);

				if (value == 0) {
					blackPaint.setColor(Color.BLACK);
				}else if (0 < value) {
					blackPaint.setColor(Color.GREEN);
				}else  {
					blackPaint.setColor(Color.RED);
				}

					canvas.drawText(text, (i + .5f) * cellWidth - padding / 2, (j + .6f) * cellHeight, blackPaint);
			}
		}
		blackPaint.setColor(Color.BLACK);
		for (int i = 0; i <= numColumns; i++) {
			canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
		}

		for (int i = 0; i <= numRows; i++) {
			canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
		}
		float y = height - 1;
		canvas.drawLine(0, y, getWidth(), y, blackPaint);
		int x = getWidth()- 1;
		canvas.drawLine(x, 0, x, height, blackPaint);
	}
}
