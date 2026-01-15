public class GPTransffered {

    public GP gp;
    public Tree treeForTransfer;
    public double[] gpParams;
    public Tree bestTree;

    public GPTransffered(double[] gpParams) {
        this.gpParams = gpParams;
        this.treeForTransfer = null;
        this.bestTree = null;
    }

    public void trainGP() {
        for (int i = 0; i < 5; i++) {
            gpParams[0] = gpParams[0]+1;
            gp = new GP((int) gpParams[0], (int) gpParams[1], (int) gpParams[2], (int) gpParams[3], gpParams[4],
                    gpParams[5]);
            gp.train();
            gp.test();
        if(treeForTransfer == null || treeForTransfer.avgFitness>gp.bestTree.avgFitness) {treeForTransfer = gp.bestTree;}
        // System.out.println("Curr: " + gp.bestTree.avgFitness);
        // System.out.println("Best Fitness: " + treeForTransfer.avgFitness);
        // System.out.println("Tree structure: "+ treeForTransfer.toString());
        }
        // System.out.println("-----------------------");
        // System.out.println("Best Fitness: " + treeForTransfer.avgFitness);
        // System.out.println("Tree structure: "+ treeForTransfer.toString());
    }

    public void transferAndCreate(double[] gpParams, double copies,double mutants,double newTrees){
        this.gpParams = gpParams;
        
        gp = new GP((int) gpParams[0], (int) gpParams[1], (int) gpParams[2], (int) gpParams[3], gpParams[4],
        gpParams[5], treeForTransfer, copies, mutants, newTrees);

    }

    public void trainGPT(){
        gp.bestTree = null;
        gp.train();
        this.treeForTransfer = gp.bestTree;
        
    }

    public void testGPT(){
        gp.test();
        this.bestTree = gp.bestTree;
    }




}
