package simpl.interpreter;




import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

/*public class Mem extends HashMap<Integer, Value> {

    private static final long serialVersionUID = -1155291135560730618L;
}*/

public class Mem
{
    public Value[] cell;
    public boolean[] mark;
    private LinkedBlockingQueue<Integer> FreeList;
    private static int maxElement =5;

    public Mem()
    {
        cell = new Value[1000];
        mark = new boolean[1000];
        for (int i = 0; i < 1000; i++)
        {
            mark[i] = false;
            cell[i] = null;
        }
        FreeList = new LinkedBlockingQueue<Integer>(1000);
        /*
        For Map & Sweep
        */
        for (int i = 0; i < maxElement; i++)
        {
            FreeList.add(i);
        }
    }

    public int GetFreeSpace(Env E) throws RuntimeError
    {
        if ((FreeList.peek())!= null)
        {
            return FreeList.poll();
        }
        else
        {
            for (int i = 0; i < 1000; i++) mark[i] = false;
            E.mark(this);
            for (int i = 0; i < maxElement; i++)
            {
                if (!mark[i])
                {
                    FreeList.add(i);
                    cell[i] = null;
                }
            }
        }
        if (FreeList.peek() != null)
            return FreeList.poll();
        else throw new RuntimeError("No Enough Memory Space");
    }


    public Value put(int i, Value p)
    {
        cell[i] = p;
        mark[i] = false;
        return p;
    }

    public void print()
    {
        for (int i=0; i<maxElement; i++)
        {
            if (cell[i] == null)
            {
                System.out.print("NULL ");
            }
            else System.out.print(cell[i]+" ");
        }
        System.out.println();
        /*for (int i=0; i<maxElement; i++)
        {
            System.out.print(mark[i]+"    ");
        }
        System.out.println();*/
    }

    public Value get(int i)
    {
        return cell[i];
    }
    public Boolean getMark(int i)
    {
        return mark[i];
    }
}