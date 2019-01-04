package algorithms;

import java.awt.Point;
import java.util.ArrayList;

/***************************************************************
 * TME 1: calcul de diametre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés d'un ensemble de*
 *     points donné en entrée.                               *
 *   - Couvrir l'ensemble de points donnee en entree par un    *
 *     cercle de rayon minimum.                                *
 *                                                             *
 * class Circle:                                               *
 *   - Circle(Point c, int r) constructs a new circle          *
 *     centered at c with radius r.                            *
 *   - Point getCenter() returns the center point.             *
 *   - int getRadius() returns the circle radius.              *
 *                                                             *
 * class Line:                                                 *
 *   - Line(Point p, Point q) constructs a new line            *
 *     starting at p ending at q.                              *
 *   - Point getP() returns one of the two end points.         *
 *   - Point getQ() returns the other end point.               *
 ***************************************************************/
import supportGUI.Circle;
import supportGUI.Line;

public class Draft1 {

  // calculDiametre: ArrayList<Point> --> Line
  // renvoie une paire de points de la liste, de distance maximum.
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size()<3) {
      if (points.size()<2) { return null; }
      else { return new Line(points.get(0),points.get(1)); }
    }

    Point p=points.get(0);
    Point q=points.get(1);
    double distance_max = distance(p,q);
    
    for (int i = 0; i < points.size(); i++) {
    	for (int j = i+1; j < points.size(); j++) {
    		
    		double d = distance(points.get(i),points.get(j));
			if(distance_max < d) {
				distance_max = d;
				p=points.get(i);
				q=points.get(j);
			}
		}
		
	}
    return new Line(p,q);
  }

  // calculCercleMin: ArrayList<Point> --> Circle
  // renvoie un cercle couvrant tout point de la liste, de rayon minimum.
  public Circle calculCercleMin(ArrayList<Point> points) {
    if (points.isEmpty()) {
      return null;
    }

    Point center=points.get(0);
    int radius=100;
    
    /** Algorithme naif ******************/
    if(points.size() > 1) {
	    Line diametre = calculDiametre(points);
	    center = barycentre(diametre.getP(), diametre.getQ());
	    radius = (int) distance(diametre.getP(), diametre.getQ())/2;
    }
    /*************************************/

    return new Circle(center,radius);
  }
  
  /** Ajout pour tme **/
  private double distance(Point p, Point q) {
	  return Math.sqrt((q.getX() - p.getX())* (q.getX() - p.getX()) + (q.getY() - p.getY())* (q.getY() - p.getY()));
  }
  
  /** Ajout pour tme **/
  private Point barycentre(Point p, Point q) {
	  double x = (p.getX()+q.getX())/2;
	  double y = (p.getY()+q.getY())/2;
	  Point res = new Point();
	  res.setLocation(x, y);
	  return res;
  }

}
