package com.zaietsv.images_comparator_old_bad_and_slow;

import java.awt.Point;
import java.awt.Polygon;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Area class for ImagesPanel class purposes.
 * Specifies polygonal area for a set of points.
 * */

class Area {
	
	//fields
	private Point initialPoint;
	private LinkedBlockingQueue<Point> areaPoints = new LinkedBlockingQueue<Point>();
	
	//constructors
	public Area() {
	}
	
	public Area(Point initialPoint, LinkedBlockingQueue<Point> area) {
		this.initialPoint = initialPoint;
		this.areaPoints = area;
	}
	
	//methods
	//getters and setters
	void setInitialPoint(Point initialPoint) {
	    this.initialPoint = initialPoint;
	}
	
	void setArea(LinkedBlockingQueue<Point> area) {
	    this.areaPoints = area;
	}
	  
	Point getInitialPoint() {
	    return initialPoint;
	}
	
	LinkedBlockingQueue<Point> getArea() {
	    return areaPoints;
	}
	
	//creates Polygon instance using specified points set
	public Polygon getPolygon() {
		Polygon polygon = new Polygon();
			for (Point p : areaPoints) {
				polygon.addPoint(p.x, p.y);
			}
		polygon.addPoint(initialPoint.x, initialPoint.y);
		return polygon;
	}
}