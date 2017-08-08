package cn.edu.cup.lps

/*
* 水力学工程
* 就是程序中的工程，是整个计算的总领
* 20170729
* */

class HydraulicProject {

    String name

    PipeNetwork pipeNetwork             //管网
    BoundaryCondition boundaryCondition //边界条件
    InitialCondition initialCondition   //初始条件
    EventSequence eventSequence         //事件序列

    static constraints = {
        name(unique: true)
        pipeNetwork(nullable: true)
        initialCondition(nullable: true)
        boundaryCondition(nullable: true)
        eventSequence(nullable: true)
    }

    String toString() {
        return "${name}[${pipeNetwork}.${eventSequence}]"
    }

}
