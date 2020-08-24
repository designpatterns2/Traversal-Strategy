/*
 * Copyright 2008-2014, David Karnok 
 * The file is part of the Open Imperium Galactica project.
 * 
 * The code should be distributed under the LGPL license.
 * See http://www.gnu.org/licenses/lgpl.html for details.
 */

package hu.openig.ui;

import hu.openig.core.Action0;
import hu.openig.render.TextRenderer;
import hu.openig.ui.UIMouse.Type;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

/**
 * A radiobutton component with text.
 * @author akarnokd, Apr 18, 2011
 */
public class UIRadioButton extends UIComponent {
	/** The text to display. */
	protected String text;
	/** The text and checkbox size. */
	protected int size;
	/** The text renderer. */
	protected final TextRenderer tr;
	/** The normal color. */
	protected int color = TextRenderer.GREEN;
	/** The disabled color. */
	protected int disabledColor = TextRenderer.GRAY;
	/** The selection state. */
	protected boolean selected;
	/** The vertical alignment. */
	protected VerticalAlignment valign;
	/** The change handler. */
	public Action0 onChange;
	/** The background color. */
	protected int backgroundColor = 0xC0000000;
	/**
	 * Construct an UICheckBox.
	 * @param text the text label
	 * @param size the size
	 * @param tr the text renderer
	 */
	public UIRadioButton(String text, int size, TextRenderer tr) {
		this.text = text;
		this.size = size;
		this.height = size;
		this.width = tr.getTextWidth(size, text) + size + 6;
		this.tr = tr;
	}
	/** @return the selected state. */
	public boolean selected() {
		return selected;
	}
	/**
	 * Change the selection state.
	 * @param state the new state
	 * @return this
	 */
	public UIRadioButton selected(boolean state) {
		this.selected = state;
		return this;
	}
	@Override
	public void draw(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		if (valign == VerticalAlignment.BOTTOM) {
			g2.translate(0, height - size);
		} else
		if (valign == VerticalAlignment.MIDDLE) {
			g2.translate(0, (height - size) / 2);
		}
		
		if (backgroundColor != 0) {
	 		g2.setColor(new Color(backgroundColor, true));
			g2.fillRect(-5, -5, width + 10, height + 10);
		}

		g2.setColor(enabled ? Color.WHITE : new Color(disabledColor));
		Stroke sp = g2.getStroke();
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(0, 0, size - 1, size - 1);
		g2.setStroke(sp);
		if (selected) {
			g2.fillOval(3, 3, size - 7, size - 7);
		}
		
		
		tr.paintTo(g2, size + 6, 0, size, enabled() ? color : disabledColor, text);
		
		g2.setTransform(at);
	}
	@Override
	public boolean mouse(UIMouse e) {
		if (e.has(Type.DOWN)) {
			selected = true;
			if (onChange != null) {
				onChange.invoke();
			}
			return true;
		}
		return super.mouse(e);
	}
	/**
	 * Change the text.
	 * @param newText the new text
	 * @param resize resize to the new text?
	 * @return this
	 */
	public UIRadioButton text(String newText, boolean resize) {
		this.text = newText;
		if (resize) {
			sizeToContent();
		}
		return this;
	}
	/**
	 * @return returns the current text color
	 */
	public int color() {
		return this.color;
	}
	/**
	 * Sets the text color.
	 * @param newColor the new color
	 * @return this
	 */
	public UIRadioButton color(int newColor) {
		this.color = newColor;
		return this;
	}
	/**
	 * @return the current vertical alignment
	 */
	public VerticalAlignment vertically() {
		return valign;
	}
	/**
	 * Set the vertical alignment.
	 * @param valign the new alignment
	 * @return this
	 */
	public UIRadioButton vertically(VerticalAlignment valign) {
		this.valign = valign;
		return this;
	}
	/**
	 * Size checkbox to content.
	 */
	public void sizeToContent() {
		width = tr.getTextWidth(size, text) + size + 6;
	}
	/**
	 * Change the current display text.
	 * @param newText the new text
	 * @return this
	 */
	public UIRadioButton text(String newText) {
		return text(newText, false);
	}
	/**
	 * @return the current background color ARGB
	 */
	public int backgroundColor() {
		return backgroundColor;
	}
	/**
	 * Set the background color.
	 * @param newColor the new color ARGB
	 * @return this
	 */
	public UIRadioButton backgroundColor(int newColor) {
		this.backgroundColor = newColor;
		return this;
	}
}
