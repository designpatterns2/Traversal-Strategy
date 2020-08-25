/*
 * Copyright 2008-2014, David Karnok 
 * The file is part of the Open Imperium Galactica project.
 * 
 * The code should be distributed under the LGPL license.
 * See http://www.gnu.org/licenses/lgpl.html for details.
 */

package hu.openig.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to indicate a simple BufferedImage to be loaded.
 * @author akarnokd, 2009.11.09.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Img {
	/** The path to the resource. */
	String name();
}
