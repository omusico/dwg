/*
 * Created on 03-feb-2007
 *
 * gvSIG. Sistema de Informaci�n Geogr�fica de la Generalitat Valenciana
 *
 * Copyright (C) 2004 IVER T.I. and Generalitat Valenciana.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,USA.
 *
 * For more information, contact:
 *
 *  Generalitat Valenciana
 *   Conselleria d'Infraestructures i Transport
 *   Av. Blasco Ib��ez, 50
 *   46010 VALENCIA
 *   SPAIN
 *
 *      +34 963862235
 *   gvsig@gva.es
 *      www.gvsig.gva.es
 *
 *    or
 *
 *   IVER T.I. S.A
 *   Salamanca 50
 *   46005 Valencia
 *   Spain
 *
 *   +34 963163400
 *   dac@iver.es
 */
/* CVS MESSAGES:
*
* $Id: DwgVertexPFaceFace.java 28969 2009-05-25 13:23:12Z jmvivo $
* $Log$
* Revision 1.2.2.2  2007-03-21 19:49:16  azabala
* implementation of dwg 12, 13, 14.
*
* Revision 1.5  2007/03/20 19:57:08  azabala
* source code cleaning
*
* Revision 1.4  2007/03/06 19:39:38  azabala
* Changes to adapt dwg 12 to general architecture
*
* Revision 1.3  2007/03/02 20:31:22  azabala
* *** empty log message ***
*
* Revision 1.2  2007/02/07 12:44:27  fdiaz
* A�adido o modificado el metodo clone para que el DwgObject se encargue de las propiedades comunes a todos los objetos.
* A�adido el metodo fill.
*
* Revision 1.1  2007/02/05 07:03:22  azabala
* *** empty log message ***
*
*
*/
package org.gvsig.dwg.lib.objects;

import org.gvsig.dwg.lib.DwgObject;
import org.gvsig.dwg.lib.IDwgVertex;

/**
 * A vertex whose superentity is a DwgPFacePolyline.
 * 
 * This vertex hasent coordinates (instead, it has a colection of
 * indexes of the vertices which forms its faces)
 * 
 * @author azabala
 * */
public class DwgVertexPFaceFace extends DwgObject implements IDwgVertex{

	private int flags;
	private double[] vertices;
	private int[] verticesidx;

	public DwgVertexPFaceFace(int index) {
		super(index);
		vertices = new double[]{0,0,0,0};
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	
	public int getFlags() {
		return flags;
	}

	public void setPoint(double[] ds) {
		this.vertices = ds;
	}

	public double[] getPoint() {
		return vertices;
	}
	public Object clone(){
		DwgVertexPFaceFace obj = new DwgVertexPFaceFace(index);
		this.fill(obj);
		return obj;
	}
	
	protected void fill(DwgObject obj){
		super.fill(obj);
		DwgVertexPFaceFace myObj = (DwgVertexPFaceFace)obj;

		myObj.setFlags(flags);
		myObj.setPoint(vertices);
	}

	public int[] getVerticesidx() {
		return verticesidx;
	}

	public void setVerticesidx(int[] verticesidx) {
		this.verticesidx = verticesidx;
	}
	
	public String toString(){
		return "[1="+verticesidx[0]+", 2="+verticesidx[1]+", 3="+verticesidx[2]+", 4="+verticesidx[3]+"]";
	}

	

}

