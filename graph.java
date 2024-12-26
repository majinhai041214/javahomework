import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class graph implements GPH{
    Node[] nodes;
    List<Node> list;
    int size;
    public void graph(){
        list=new ArrayList<>();
    }
    public void graph(ArrayList lst){
        list=lst;
        size=lst.size();
        nodes= (Node[]) list.toArray();
    }
    @Override
    public void insert(Node nd) {
        list.add(nd);
        nodes= (Node[]) list.toArray();
    }

    @Override
    public void delete(Node nd) {

    }

    @Override
    public int minDistance(Node nd1, Node nd2) {
        return 0;
    }

    public String[] filrRead() throws FileNotFoundException {
        File file = new File("D:/Java/homework4/homework4/words5.txt");
        Scanner sc=new Scanner(file);
        ArrayList words=new ArrayList();
        int wordnum=0;
        while(sc.hasNextLine()){
            String str=sc.nextLine();
            words.add(str);
            wordnum++;
        }
        String[] wordsarr=new String[wordnum];
        for(int i=0;i<wordnum;i++){
            wordsarr[i]=words.get(i).toString();
        }
        System.out.println("File read successfully");
        return wordsarr;
    }

    /*构造图*/
    private List[] graphy(String[] words) {
        int size=words.length;
        List[] wordsarr=new List[size];/*创建初始链表数组，每个元素都是链表的头节点->某个单词*/
        for(int i=0;i<size;i++){
            List<Node> list=new ArrayList<>();
            list.add(new Node(words[i]));
            /*针对数组的特定元素构建链表*/
            for(int j=0;j<size;j++){
                char[] character=words[i].toCharArray();
                char[] character1=words[j].toCharArray();
                int samecount=0;
                for(int k=0;k<5;k++){
                    if(character[k]==character1[k]){
                        samecount++;
                    }
                }
                if(samecount==4){
                    list.add(new Node(words[j]));
                }
            }
            wordsarr[i]=list;
        }
        System.out.println("Graph constructed successfully");
        return wordsarr;
    }

    public static String[] printGraph(List[] wordsarr) throws FileNotFoundException {
        PrintWriter pw =new PrintWriter("D:/Java/homework4/homework4/showgraph.txt");
       PrintWriter pw1=new PrintWriter("D:/Java/homework4/homework4/graphwithnoedge.txt");
       PrintWriter pw2=new PrintWriter("D:/Java/homework4/homework4/graphhaveedge.txt");
       System.out.println("Graph printing...");
       pw1.println("1");
       ArrayList<String> errowords=new ArrayList<>();
       ArrayList<String> rightwords=new ArrayList<>();

       int i=0;
        for(List word:wordsarr) {
            int nextcount=0;
           for (Node nd : (List<Node>) word){
               pw.print(nd.str + "->");
                nextcount++;
           }
           pw.print(nextcount);
            if(nextcount == 1) {
                pw1.println(((Node) word.get(0)).str);
                errowords.add(((Node) word.get(0)).str);
                pw1.println(1);
            }else{
                pw2.println(((Node) word.get(0)).str);
                rightwords.add(((Node) word.get(0)).str);
            }
           pw.println();
       }
       System.out.println("Graph printed successfully");
        pw.close();
        pw1.close();
        pw2.close();
        String[] errowordsarr=new String[errowords.size()];
        String[] rightwordsarr=new String[rightwords.size()];
        for(int j=0;j<errowords.size();j++){
            errowordsarr[j]=errowords.get(j);
        }
        for(int j=0;j<rightwords.size();j++){
            rightwordsarr[j]=rightwords.get(j);
        }
        return rightwordsarr;
    }

    public static void main(String[] args) throws FileNotFoundException {
        /*游戏初始化*/
        graph g=new graph();
        String[] words=g.filrRead();
        List[]wordarry=g.graphy(words);

        /*用户交互*/
        System.out.println();
        System.out.println("Game start!");
        Random random=new Random();
        int randomstart, randomlength;
        /*生成一个有相邻元素的随机元素*/
        do{
            randomstart=random.nextInt(wordarry.length);
        }while(wordarry[randomstart].size()<=3);

        /*生成游戏终止元素*/
        do{
            randomlength=random.nextInt(wordarry.length);
        }while(randomlength<=1||randomlength>wordarry[randomstart].size());
        Node startnode=(Node)wordarry[randomstart].get(0);
        String startword=startnode.str;/*起始元素*/
        /*终止元素*/
        Node endnode=(Node)wordarry[randomstart].get(randomlength-1);
        String endword=endnode.str;

        System.out.println("Start word is :"+startword);
        System.out.println("End word is :"+endword);
        System.out.println("the next word is :"+((Node)wordarry[randomstart].get(1)).str);
        int i=1;
    do{
        System.out.println("Please input the word you guess:");
        Scanner sc =new Scanner(System.in);
        String guessword=sc.nextLine();
        Node nextnode=(Node)wordarry[randomstart].get(i);
        String nextword=nextnode.str;
        if(guessword.equals(nextword)){
            System.out.println("You are right!");
            i++;
        }else{
            System.out.println("You are wrong!");
        }
    }while(i<randomlength);
        System.out.println("Game end!");


        /*12月27日：
        * 基本的游戏框架已经有了并且可以正常地进行用户的交互
        * 接下来的任务：
        * 找到两个结点之间的最短路径并于用户交互*/
    }
}
