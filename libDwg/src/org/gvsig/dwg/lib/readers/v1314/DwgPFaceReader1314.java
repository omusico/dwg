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
* $Id: DwgPFaceReader1314.java 28969 2009-05-25 13:23:12Z jmvivo $
* $Log$
* Revision 1.1.2.2  2007-03-21 19:49:16  azabala
* implementation of dwg 12, 13, 14.
*
* Revision 1.3  2007/03/20 19:57:09  azabala
* source code cleaning
*
* Revision 1.2  2007/03/01 19:56:52  azabala
* refactor of names
*
* Revision 1.1  2007/02/05 07:03:22  azabala
* *** empty log message ***
*
*
*/
package org.gvsig.dwg.lib.readers.v1314;

import java.util.List;

import org.gvsig.dwg.lib.CorruptedDwgEntityException;
import org.gvsig.dwg.lib.DwgHandleReference;
import org.gvsig.dwg.lib.DwgObject;
import org.gvsig.dwg.lib.DwgUtil;
import org.gvsig.dwg.lib.objects.DwgPFacePolyline;


public class DwgPFaceReader1314 extends AbstractDwg1314Reader {

	public void readSpecificObj(int[] data, int offset, DwgObject dwgObj)
			throws RuntimeException, CorruptedDwgEntityException {
		
		if(! (dwgObj instanceof DwgPFacePolyline))
	    	throw new RuntimeException("ArcReader 14 solo puede leer DwgPFacePolyline");
		DwgPFacePolyline pface = (DwgPFacePolyline) dwgObj;
		int bitPos = offset;
		bitPos = headTailReader.readObjectHeader(data, bitPos, pface);
		
		List val = DwgUtil.getBitShort(data, bitPos);
		bitPos = ((Integer) val.get(0)).intValue();
		int vertexCount = ((Integer) val.get(1)).intValue();
		pface.setVertexCount(vertexCount);
		
		val = DwgUtil.getBitShort(data, bitPos);
		bitPos = ((Integer) val.get(0)).intValue();
		int faceCount = ((Integer) val.get(1)).intValue();
		pface.setFaceCount(faceCount);
		
		bitPos = headTailReader.readObjectTailer(data, bitPos, pface);
		
		DwgHandleReference handle = new DwgHandleReference();
		bitPos = handle.read(data, bitPos);
		pface.setFirstVertexHandle(handle);
		
		handle = new DwgHandleReference();
		bitPos = handle.read(data, bitPos);
		pface.setLastVertexHandle(handle);
		
		handle = new DwgHandleReference();
		bitPos = handle.read(data, bitPos);
		pface.setSeqendHandle(handle);
	}

}

