package course_prerequisites;

/**
 *
 * @author VINAY
 */

import java.io.*;
import java.util.*;

public class Prerequisites {

    Stack<Node> stack;

    public Prerequisites() {
        stack = new Stack<>();
    }

    static class Node {

        String data;
        boolean visited;
        List<Node> neighbours;

        Node(String data) {
            this.data = data;
            this.neighbours = new ArrayList<>();

        }

        public void addneighbours(Node neighbourNode) {
            this.neighbours.add(neighbourNode);
        }

        public List<Node> getNeighbours() {
            return neighbours;
        }

        public void setNeighbours(List<Node> neighbours) {
            this.neighbours = neighbours;
        }

        public String toString() {
            return data;
        }
    }

    static Node c1 = new Node("Data Structure and Algorithms");
    static Node c2 = new Node("Applied Probability and Statistics");
    static Node c3 = new Node("Operating Systems and Networks");
    static Node c4 = new Node("Software Engineering");
    static Node c5 = new Node("Software Architecture");
    static Node c6 = new Node("Design and Analysis of Algorithms");
    static Node c7 = new Node("Database Management Systems");
    static Node c8 = new Node("Advanced Database Management Systems");
    static Node c9 = new Node("Data Mining");
    static Node c10 = new Node("Software Metrics and Project Management");

    static Node req1 = new Node("Any programming language");
    static Node req2 = new Node("Basic Mathematics needed for programming");
    static Node req3 = new Node("Knowledge of Computers");
    static Node req4 = new Node("Permutational and Combinational Problems");
    static Node req5 = new Node("Basics of Probability");
    static Node req6 = new Node("Calculus");
    static Node req7 = new Node("Event");
    static Node req8 = new Node("Sample Space");
    static Node req9 = new Node("Basic knowledge of Statistics");
    static Node req10 = new Node("Knowledge of Computer Hardware and Software");
    static Node req11 = new Node("Software Development Process");
    static Node req12 = new Node("Awareness about Software Systems");

    public void toplogicalSort(Node node) {
        List<Node> neighbours = node.getNeighbours();
        for (int i = 0; i < neighbours.size(); i++) {
            Node n = neighbours.get(i);
            if (n != null && !n.visited) {
                toplogicalSort(n);
                n.visited = true;
            }
        }
        stack.push(node);
    }

    public Node addMorePrerequisites(Node node) {
        Prerequisites ts2 = new Prerequisites();
        Node n = null;
        switch (node.data) {
            case "Data Structure and Algorithms":
                c1.addneighbours(req1);
                req1.addneighbours(req2);
                n = req2;
                break;
            case "Applied Probability and Statistics":
                c2.addneighbours(req4);
                req4.addneighbours(req5);
                req5.addneighbours(req6);
                req6.addneighbours(req7);
                req7.addneighbours(req8);
                req8.addneighbours(req9);
                n = req9;
                break;
            case "Operating Systems and Networks":
                c3.addneighbours(req10);
                n = req10;
                break;
            case "Software Engineering":
                c4.addneighbours(req11);
                req11.addneighbours(req12);
                n = req12;
                break;
            case "Software Architecture":
                c5.addneighbours(c4);
                c4.addneighbours(req11);
                req11.addneighbours(req12);
                n = req12;
                break;
            case "Database Management Systems":
                c7.addneighbours(c1);
                n = c1;
                break;
        }
        return n;
    }

    public static void main(String arg[]) {

        Scanner sc = new Scanner(System.in);
        Prerequisites ts = new Prerequisites();
        String opt = "";
        String course = "";
        try {
            System.out.println("Enter filename of the dataset  :");
            String name = sc.nextLine();

            File currentDir = new File("");
            String path = currentDir.getAbsolutePath();
            File file = new File(path + "\\test_inputs\\" + name + ".txt");
            Scanner c = new Scanner(file);
            ArrayList<String> line = new ArrayList<String>();

            int i = 0;
            while (c.hasNext()) {
                line.add(c.nextLine());
                i++;
            }
            System.out.println("");
            System.out.println("------------------------------");
            int j = 0;

            for (String s : line) {
                System.out.println(s);
                StringTokenizer st1 = new StringTokenizer(s, ":");
                int tk = 0;

                while (st1.hasMoreTokens()) {
                    String token = st1.nextToken();
                    if (tk == 1 && j == 1) {
                        course = token;
                        opt = course.toLowerCase();
                    }
                    tk++;
                }
                j++;
            }

            System.out.println("------------------------------");
            if (!course.isEmpty()) {

                switch (opt) {
                    case "data structure and algorithms":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c1.addneighbours(req1);
                        req1.addneighbours(req2);
                        req2.addneighbours(req3);
                        ts.toplogicalSort(req1);
                        break;
                    case "applied probability and statistics":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c2.addneighbours(req4);
                        req4.addneighbours(req5);
                        req5.addneighbours(req6);
                        req6.addneighbours(req7);
                        req7.addneighbours(req8);
                        req8.addneighbours(req9);
                        ts.toplogicalSort(req4);
                        break;
                    case "operating systems and networks":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c3.addneighbours(req10);
                        req10.addneighbours(req3);
                        ts.toplogicalSort(req10);
                        break;
                    case "software engineering":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c4.addneighbours(req11);
                        req11.addneighbours(req12);
                        req12.addneighbours(req3);
                        ts.toplogicalSort(req11);
                        break;
                    case "software architecture":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c5.addneighbours(c4);
                        Node m = ts.addMorePrerequisites(c4);
                        m.addneighbours(req3);
                        ts.toplogicalSort(c4);
                        break;
                    case "design and analysis of algorithms":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c6.addneighbours(c1);
                        Node n = ts.addMorePrerequisites(c1);
                        n.addneighbours(req3);
                        ts.toplogicalSort(c1);
                        break;
                    case "database management systems":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c7.addneighbours(c1);
                        Node o = ts.addMorePrerequisites(c1);
                        o.addneighbours(req3);
                        ts.toplogicalSort(c1);
                        break;
                    case "advanced database management systems":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c8.addneighbours(c7);
                        Node p = ts.addMorePrerequisites(c7);
                        Node q = ts.addMorePrerequisites(p);
                        q.addneighbours(req3);
                        ts.toplogicalSort(c7);
                        break;
                    case "data mining":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c9.addneighbours(c7);
                        Node r = ts.addMorePrerequisites(c7);
                        Node s = ts.addMorePrerequisites(r);
                        s.addneighbours(req3);
                        ts.toplogicalSort(c7);
                        break;
                    case "software metrics and project management":
                        System.out.println("Prerequisites for " + course);
                        System.out.println("------------------------------");
                        c10.addneighbours(c4);
                        Node t = ts.addMorePrerequisites(c4);
                        t.addneighbours(req3);
                        ts.toplogicalSort(c4);
                        break;

                    default:
                        System.out.println("Sorry! This course is not available in the course list...!");

                }

                // Print contents of stack
                Stack<Node> resultStack = ts.stack;
                while (resultStack.empty() == false) {
                    System.out.print(resultStack.pop() + "\n");
                }
                System.out.println("------------------------------");
            }

        } catch (Exception e) {

        }
    }

}

