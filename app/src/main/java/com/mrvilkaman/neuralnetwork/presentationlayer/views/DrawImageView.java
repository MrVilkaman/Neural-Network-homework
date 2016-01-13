package com.mrvilkaman.neuralnetwork.presentationlayer.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawImageView extends View {

	private int numColumns, numRows;
	private int cellWidth, cellHeight;
	private Paint blackPaint = new Paint();
	private boolean[][] matrix;

	private boolean black = true;

	public DrawImageView(Context context) {
		super(context);
		init();
	}

	public DrawImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DrawImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
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

		matrix = new boolean[numColumns][numRows];

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
				if (matrix[i][j]) {
					canvas.drawRect(i * cellWidth, j * cellHeight, (i + 1) * cellWidth, (j + 1) * cellHeight, blackPaint);
				}
			}
		}

		for (int i = 0; i <= numColumns; i++) {
			canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
		}

		for (int i = 0; i <= numRows; i++) {
			canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
		}
		float y = height - 1;
		canvas.drawLine(0, y, getWidth(), y, blackPaint);
		int x = getWidth() - 1;
		canvas.drawLine(x, 0, x, height, blackPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) (event.getX() / cellWidth);
		int y = (int) (event.getY() / cellHeight);

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				touchMove(x, y);
				break;
		}
		return true;
	}

	private void touchMove(int x, int y) {
		if (0 <= x && (x < matrix.length) && 0 <= y  &&  (y < matrix[x].length) && isEnabled()) {

			boolean needUpdate = matrix[x][y] != isBlack();
			if (needUpdate) {
				matrix[x][y] = isBlack();
				invalidate();
			}
		}
	}

	public void clear() {
		calculateDimensions();
	}

	public void setBlack(boolean black) {
		this.black = black;
	}

	public boolean isBlack() {
		return black;
	}

	public boolean[][] getCellMatrix() {
		return matrix.clone();
	}
}
