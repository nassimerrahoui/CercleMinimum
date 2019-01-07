package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/***************************************************************
 * TME 1: calcul de diametre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés dun ensemble de*
 *     points donné en entrée.                               *
 *   - Couvrir lensemble de points donnee en entree par un    *
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

public class DefaultTeam {

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
    double radius=100;
    
    /** Algorithme Ritter ******************/
    
    // Copie pour garder les points sur affichage
    ArrayList<Point> pointsAlgo = new ArrayList<>(points);
    
    // Etape 1
    Random rand = new Random();
    int randomElement = rand.nextInt(pointsAlgo.size());
    Point dummy = pointsAlgo.get(randomElement);
    
    // Etape 2:
    double distance_max = 0;
    Point p = new Point();
    
    for (Point point : pointsAlgo){
    	double d = distance(dummy,point);
    	if (d>distance_max) {
    		distance_max = d;
    		p = point;
    	}
	}
    
    // Etape 3
    distance_max = 0;
    Point q = new Point();
    for (Point point : pointsAlgo){
    	double d = distance(p,point);
    	if (d>distance_max) {
    		distance_max = d;
    		q = point;		
    	}
	}
    
    // Etape 4
    center = barycentre(p,q);
    
    // Etape 5
    radius = distance(p,q)/2;
    
    // Etape 6
    pointsAlgo.remove(p);
    pointsAlgo.remove(q);
    
    // Etapes...
    while(!pointsAlgo.isEmpty()) {
        
        randomElement = rand.nextInt(pointsAlgo.size());
        Point s = pointsAlgo.get(randomElement);
        double distance_cs = distance(center, s);
        
        if(distance_cs <= radius) {
        	pointsAlgo.remove(s);
        }
        else {
        	radius = (distance_cs + radius) / 2;
        	double alpha = radius / distance_cs;
        	double beta = 1 - alpha;
        	
        	// Calcul du nouveau centre
        	double x = alpha * center.getX() + beta * s.getX();
        	double y = alpha * center.getY() + beta * s.getY();
        	center.setLocation(x, y);
        	
        	pointsAlgo.remove(s);
        }
    }
    
    /*************************************/

    return new Circle(center,(int) radius);
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
  
//  /** Ajout pour tme **/
//  private double crossProduct(Point p, Point q, Point s, Point t){
//      return ((q.x-p.x)*(t.y-s.y)-(q.y-p.y)*(t.x-s.x));
//  }
}
