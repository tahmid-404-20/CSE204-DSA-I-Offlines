import java.util.Scanner;

class Station {

    int stationNo;
    boolean isRickshawStop;
    boolean isBusStop;
    boolean isTrainStation;

    public Station() {
        isRickshawStop = isBusStop = isTrainStation = false;
    }
}

public class TNL {

    static void printTNL(AL<Station> tnl) {
        int nStations = tnl.length();
        int curr = tnl.currPos();

        tnl.moveToStart();
        for (int i = 0; i < nStations; i++) {
            Station station = tnl.getValue();

            if (station.isRickshawStop)
                System.out.print(i);

            if (i != (nStations - 1))
                System.out.print(",");          // No comma at the end of print format

            tnl.next();
        }
        System.out.println();

        tnl.moveToStart();
        for (int i = 0; i < nStations; i++) {
            Station station = tnl.getValue();

            if (station.isBusStop)
                System.out.print(i);

            if (i != (nStations - 1))
                System.out.print(",");

            tnl.next();
        }
        System.out.println();

        tnl.moveToStart();
        for (int i = 0; i < nStations; i++) {
            Station station = tnl.getValue();

            if (station.isTrainStation)
                System.out.print(i);

            if (i != (nStations - 1))
                System.out.print(",");

            tnl.next();
        }
        System.out.println();

        tnl.moveToPos(curr);
    }

    public static void main(String[] args) {
        int nRs, nBs, nTs;

        Scanner scr = new Scanner(System.in);

        nRs = scr.nextInt();

        int[] stationType = new int[nRs];

        nBs = scr.nextInt();
        for (int i = 0; i < nBs; i++) {
            int stationIndex = scr.nextInt();
            stationType[stationIndex]++;
        }

        nTs = scr.nextInt();
        for (int i = 0; i < nTs; i++) {
            int stationIndex = scr.nextInt();
            stationType[stationIndex]++;
        }

        //Initialization type - a
        //AL<Station> tnl = new LL<>();
        AL<Station> tnl = new Arr<>(nRs);

        for (int i = 0; i < nRs; i++) {
            Station station = new Station();

            station.stationNo = i;
            station.isRickshawStop = true;

            if (stationType[i] >= 1)
                station.isBusStop = true;

            if (stationType[i] == 2)
                station.isTrainStation = true;

            tnl.append(station);
        }

        //Initialization type - b
        /*Station []stations = new Station[nRs];

        for(int i=0;i<nRs;i++)
        {
            stations[i] = new Station();

            stations[i].stationNo = i;
            stations[i].isRickshawStop = true;

            if (stationType[i] >= 1)
                stations[i].isBusStop = true;

            if (stationType[i] == 2)
                stations[i].isTrainStation = true;
        }

        AL<Station> tnl = new LL<>(stations);
        AL<Station> tnl = new Arr<>(stations, nRs);*/

        int taskNo = scr.nextInt();

        if (taskNo == 1)
            printTNL(tnl);


    }
}
