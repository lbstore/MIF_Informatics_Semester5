def ncikl(V:int, L:list, Lst:list):
    # L iš eilės surašytos viršūnių kaimynės
    # Lst iš eilės surašyti viršūnių L masyve rėžiai
    # pasiruošimas funkcijos vykdymui
    class info:
        visitedIndex = 0
        top = -1
        stack = [-1] * (len(Lst) - 1)
        nr = [0] * (len(Lst) - 1)       # nr[i] yra i-osios viršūnės aplankymo numeris
        cycles = list()                 # saugoti ciklams

    def nciklRec(v: int):
        # atvirkštinės briaunos sąlyga:
        # “viršūnė v nenauja” and “į viršūnę u buvome atėję ne iš
        # viršūnės v” and “ nr[v] < nr[u] , t.y. viršūnė v buvo aplankyta anksčiau nei viršūnė u”.

        info.top += 1
        info.stack[info.top] = v            # užrašome į stack nagrinėjama viršūnė
        info.visitedIndex += 1              # padidinamas aplankymo numeris
        info.nr[v] = info.visitedIndex      # užrašome i viršūnės vietą masyve aplankytą pagal eiliškuma viršūnę
        for i in range(Lst[v], Lst[v + 1]):  # vykdome paieška per visas v matomas viršūnes
            u = L[i]                        # u - dabar tiriama viršūnė
            if info.nr[u] == 0:             # jeigu u dar neaplankėm tada vykdom rekursiją
                nciklRec(u)
            else:
                # jeigu briauna nėra atvirkstinė ( sudarant "spanning tree" ji nepateko per tą briauną)
                # ir jeigu dabar tiriama viršūnė nėra įdėta prieš tai buvusioje iteracijoje
                # (išvengiama tokiu ciklų kaip [0,1,0] )
                # Briauna(v, u) – atvirkštinė briauna.
                # Įsiminti ciklą, einantį per viršūnes: u, stack[top], stack[top – 1], ..., stack[c], čia stack[c] = u.
                if (info.nr[v] > info.nr[u]) and (u != info.stack[info.top-1]):
                    cycle = list()
                    cycle.append(u)
                    top1 = info.top
                    while info.stack[top1] != u:
                        cycle.append(info.stack[top1])
                        top1 -= 1
                    cycle.append(u)
                    info.cycles.append(cycle)
        info.top -= 1
        return

    # iškviečiama funkcija
    nciklRec(V)
    return info.cycles


class t:
    @staticmethod
    def t1():
        L = [1,2, 0,2, 0,1]
        lst = [0,2,4,6]
        print(ncikl(0, L, lst))
    @staticmethod
    def t2():
        L = [1,3, 0,2,3, 1,3, 0,1,2]
        lst = [0,2,5,7,10]
        print(ncikl(0, L, lst))
    @staticmethod
    def t3():
        L = [1,2,3,5, 0,2,3,4,5, 0,1,3,4, 0,1,2, 1,2, 0,1]
        lst = [0,4,9,13,16,18,20]
        print(ncikl(0, L, lst))


t.t1()
t.t2()
t.t3()
