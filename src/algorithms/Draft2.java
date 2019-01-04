package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/***************************************************************
 * TME 1: calcul de diamètre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés d'un ensemble de  *
 *     points donné en entrée.                                 *
 *   - Couvrir l'ensemble de poitns donné en entrée par un     *
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

public class Draft2 {

  private double distance(Point p, Point q) {
	  //System.out.println( (q.getX() - p.getX()) * (q.getX() - p.getX()) + (q.getY() - p.getY())* (q.getY() - p.getY()));
	  return Math.sqrt((q.getX() - p.getX())* (q.getX() - p.getX()) + (q.getY() - p.getY())* (q.getY() - p.getY()));
  }
  
  private Point barycentre(Point p, Point q){
	  double x = (p.getX()+q.getX())/2;
	  double y = (p.getY()+q.getY())/2;
	  Point res = new Point();
	  res.setLocation(x, y);
	  return res;
  }
  // calculDiametre: ArrayList<Point> --> Line
  //   renvoie une paire de points de la liste, de distance maximum.
  public Line calculDiametre(ArrayList<Point> points) {
    if (points.size()<3) {
      return null;
    }

    Point p = points.get(0);
    Point q = points.get(1);
    double distance_max =  distance(p,q);
    /*******************
     * PARTIE A ECRIRE *
     *******************/
    for (int i = 1; i < (points.size()); i++) {
    
    	for (int j = i+1; j < points.size(); j++) {
    		double d = distance(points.get(i),points.get(j));
			if (d> distance_max){
				distance_max = d;
				p  = points.get(i);
				q  = points.get(j);
			}
		}
	}
    return new Line(p,q);
  }

  
  
  // calculCercleMin: ArrayList<Point> --> Circle
  //   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
  public Circle calculCercleMin(ArrayList<Point> points) {
	  
	@SuppressWarnings("unchecked")
	ArrayList<Point> pointsPourAlgo = (ArrayList<Point>) points.clone();
	  
    if (pointsPourAlgo.isEmpty()) {
      return null;
    }

    ArrayList<Point> circle_set = new ArrayList<Point>(); 
    //Point center=points.get(0);
    //int radius=100;barycentre(P,Q);
    // Etape 1:
    Random rand = new Random();
    int randomElement = rand.nextInt(pointsPourAlgo.size());
    Point dummy = pointsPourAlgo.get(randomElement);
    // Etape 2:
    double distance_max = 0;
    Point P = new Point();
    
    for (Point point : pointsPourAlgo){
    	double d = distance(dummy,point);
    	if (d>distance_max) {
    		distance_max =  d;
    		P = point;
    	}
	}
    circle_set.add(P);
    //Etape 3:
    pointsPourAlgo.remove(P);
    distance_max = 0;
    Point Q = new Point();
    for (Point point : pointsPourAlgo){
    	double d = distance(P,point);
    	if (d>distance_max) {
    		distance_max =  d;
    		Q = point;	
    		
    	}
	}
    circle_set.add(Q);
    pointsPourAlgo.remove(Q);
    //Etape 4
    Point C = barycentre(P,Q);
    Point S = new Point();
    Point T = new Point();
    //Etape 5
    double rayon_pq = distance(P,Q)/2; 
    //Etape 6
    //Etape 7
    while (pointsPourAlgo.size() > 0) {
    	randomElement = rand.nextInt(pointsPourAlgo.size());
    	System.out.println(pointsPourAlgo.size());
    	S = pointsPourAlgo.get(randomElement);
    	if (distance(C,S) < rayon_pq) {
    		circle_set.add(S);
    		pointsPourAlgo.remove(S);
    		
    	}
    	else {
    		distance_max = 0;
    		for (Point point : pointsPourAlgo) {
    			double d = distance(point,S);
    			if(d>distance_max) {
    				distance_max = d;
    				T = point;
    			}	
			}
    		pointsPourAlgo.remove(S);
    		pointsPourAlgo.remove(T);
    		rayon_pq =  distance(S,T)/2;
    		C = barycentre(S,T);
    		
    	}
    	
    }
    /*******************
     * PARTIE A ECRIRE *
     *******************/
    //System.out.println(distance(S,C));
   
    return new Circle(C,(int) distance(S,C));
  }
  
  /*
  // calculCercleMin: ArrayList<Point> --> Circle
  //   renvoie un cercle couvrant tout point de la liste, de rayon minimum.
  public Circle calculCercleMin(ArrayList<Point> points) {
    if (points.isEmpty()) {
      return null;
    }

    if (points.size() == 1) {
    	Point center=points.get(0);
        int radius=100;
        return new Circle(center,radius)
        
    }
    if(points.size() == 2) {
    	return new Circle(,);
    }
    

    Line l = calculDiametre(points);

    return new Circle(center,radius);
  }*/
  

  
}