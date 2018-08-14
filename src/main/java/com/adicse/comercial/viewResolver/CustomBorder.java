package com.adicse.comercial.viewResolver;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

public class CustomBorder implements PdfPCellEvent {

	private int border = 0;

	public CustomBorder(int border) {
		this.border = border;
	}

	@SuppressWarnings("unused")
	@Override
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		// TODO Auto-generated method stub
	      float x1 = position.getLeft() + 12;
	        float x2 = position.getRight() - 2;
	        float y1 = position.getTop() - 2;
	        float y2 = position.getBottom() + 2;
//	        PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
//	        canvas.rectangle(x1, y1, x2 - x1, y2 - y1);
	        		

		PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
		canvas.saveState();
		//canvas.stroke();
		//canvas.rectangle(x1, y1, x2 - x1, y2 - y1);
		
		setLineDash(canvas);
		if ((border & PdfPCell.TOP) == PdfPCell.TOP) {
			canvas.moveTo(position.getRight(), position.getTop());
			canvas.lineTo(position.getLeft(), position.getTop());
		}
		if ((border & PdfPCell.BOTTOM) == PdfPCell.BOTTOM) {
			canvas.moveTo(position.getRight(), position.getBottom());
			canvas.lineTo(position.getLeft(), position.getBottom());
		}
		if ((border & PdfPCell.RIGHT) == PdfPCell.RIGHT) {
			canvas.moveTo(position.getRight(), position.getTop());
			canvas.lineTo(position.getRight(), position.getBottom());
		}
		if ((border & PdfPCell.LEFT) == PdfPCell.LEFT) {
			canvas.moveTo(position.getLeft(), position.getTop());
			canvas.lineTo(position.getLeft(), position.getBottom());
		}
		canvas.stroke();
		canvas.restoreState();
	}

	public void setLineDash(PdfContentByte canvas) {
	}

}

class SolidBorder extends CustomBorder {
	public SolidBorder(int border) {
		super(border);
	}

	public void setLineDash(PdfContentByte canvas) {
	}
}

class DottedBorder extends CustomBorder {
	public DottedBorder(int border) {
		super(border);
	}

	public void setLineDash(PdfContentByte canvas) {
		canvas.setLineCap(PdfContentByte.LINE_CAP_ROUND);
		canvas.setLineDash(0, 4, 2);
	}
}

class DashedBorder extends CustomBorder {
	public DashedBorder(int border) {
		super(border);
	}

	public void setLineDash(PdfContentByte canvas) {
		canvas.setLineDash(3, 3);
	}
}
