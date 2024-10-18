#include<conio.h>
#include<stdio.h>
#include <windows.h>
#include <graphics.h>
void arrg(int p,int q,int c, int f);
void gotoxy(int x,int y);
void pantalla_dos();
void diagrama();
void funcionamiento();
int estado_q0(int cabezal,char cad_1[],char cad_2[],char cad_3[],char cad_4[]);
void estado_q1(int cabezal,char cad_1[],char cad_2[],char cad_3[],char cad_4[],int acarreo);
void estado_q2(int cabezal,char cad_1[],char cad_2[],char cad_3[],char cad_4[],int acarreo);
void estado_q3(int cabezal,char cad_1[],char cad_2[],char cad_3[],char cad_4[],int acarreo);
int margen(int x1, int y1, int x2, int y2);

int main(){
	int opc;
	//Inicio de la pantalla 1
	margen(1,0,117,29);
	gotoxy(51,2);printf("Maquinas de Turing");
	arrg(7,1,46,7);
	gotoxy(48,8);printf("%c %c %c %c 1 %c 0 %c 1 %c %c %c %c",254,179,254,179,179,179,179,254,179,254);
	gotoxy(53,10); printf("%c",94);
	gotoxy(24,12);printf("Concebida por el matematico britanico Alan Turing, es un constructo teorico");
	gotoxy(24,13);printf("que ha sentado las bases de la informatica moderna. Este modelo matematico,");
	gotoxy(24,14);printf("que puede parecer abstracto, es en esencia un automata programable capaz de");
	gotoxy(24,15);printf("resolver cualquier problema que pueda ser descrito por un algoritmo.");
	margen(40,21,80,23);
	gotoxy(41,22); system("pause");
	// Fin de la Pantalla 1
	system("cls");
	do{
		margen(1,0,117,29);
		margen(39,7,82,11);
		gotoxy(51,2);printf("Maquinas de Turing");
		gotoxy(40,8);printf("1.- Definicion de la Maquina de Turing.");
		gotoxy(40,9);printf("2.- Funcionamiento de la Maquina de Turing");
		gotoxy(40,10);printf("3.- Salir");
		margen(46,12,75,14);
		gotoxy(47,13);printf("Seleccione una opcion: [   ]\b\b\b");
		scanf("%d%*c",&opc);
		system("cls");
		margen(1,0,117,29);
		switch(opc){
			case 1:
				//Pantalla 2
	    		pantalla_dos();
				initwindow(1600, 900);
				diagrama();
				gotoxy(42,28);system("pause");
	    		closegraph();
	    		break;
	    	case 2: 
	    		//Pantalla 3
				funcionamiento();
	    		gotoxy(40,28); system("pause");
	    		break;
		}
		system("cls");
	}while(opc!=3);
	return 0;  
 }  

void gotoxy(int x,int y){  
    HANDLE hcon;  
    hcon = GetStdHandle(STD_OUTPUT_HANDLE);  
    COORD dwPos;  
    dwPos.X = x;  
    dwPos.Y= y;  
    SetConsoleCursorPosition(hcon,dwPos);  
} 

int margen(int x1, int y1, int x2, int y2){
	int x,y,i;
	x=x1;
	gotoxy(x1-1,y1); printf("%c",201);
	gotoxy(x1-1,y2); printf("%c",200);
	for(x1; x1<=x2; x1++){
		gotoxy(x1,y1); printf("%c",205);
		gotoxy(x1,y2); printf("%c",205);
	}
	gotoxy(x1,y1); printf("%c",187);
	gotoxy(x1,y2); printf("%c",188);
	y=y1+1;
	for(y; y<=(y2-1); y++){
		gotoxy(x-1,y); printf("%c",186);
		gotoxy(x1,y); printf("%c",186);
	}
}

void arrg(int p,int q ,int c, int f){
	int i,x;
	
	for(x=0; x<q; x++){
		gotoxy(c,f); printf("%c",218);  
		gotoxy(c,f+(x*2)+1); printf("%c",179);
		gotoxy(c,f+(x*2)+2); printf("%c",195);
		for(i=0; i<p-1; i++){
			gotoxy(c+((i+1)*4),f); printf("%c",194);
			gotoxy(c+(i*4)+1,f+(x*2)); printf("%c%c%c",196,196,196);
			gotoxy(c+(i*4)+1,f+(x*2)+1); printf("   %c",179);
			gotoxy(c+(i*4)+1,f+(x*2)+2); printf("%c%c%c",196,196,196,197);
			gotoxy(c+((i+1)*4),f+(x*2)+2); printf("%c",197);
		}
		gotoxy(c+(i*4)+1,f); printf("%c%c%c%c",196,196,196,191);
		gotoxy(c+(i*4)+1,f+(x*2)+1); printf("   %c",179);
		gotoxy(c+(i*4)+1,f+(x*2)+2); printf("%c%c%c%c",196,196,196,180);
	}
	gotoxy(c,f+(x*2)); printf("%c",192);
	gotoxy(c+(i*4)+4,f+(x*2)); printf("%c",217);
	for(i=0; i<p-1; i++){
		gotoxy(c+((i+1)*4),f+(x*2)); printf("%c",193);
	}
}
void pantalla_dos(){
	gotoxy(40,2);printf("Definicion Formal de la Maquina de Turing");
	gotoxy(34,3);printf("Objetivo de la Maquina de Turing: Suma Binaria ");
	gotoxy(40,5);printf("	Q=  {q0,q1,q2,q3}");
	gotoxy(40,6);printf("	%c=  {0,1}",156);
	gotoxy(40,7);printf("	r=  {0,1,_,}");
	gotoxy(40,8);printf("	S=  {q0}");
	gotoxy(40,9);printf("	F=  {q3}");
	gotoxy(40,11);printf("&(q0,(0,0,_,_))->(q0,(0,0,_,_),(R,R,R,S))");
	gotoxy(40,12);printf("&(q0,(0,1,_,_))->(q0,(0,1,_,_),(R,R,R,S))");
    gotoxy(40,13);printf("&(q0,(1,0,_,_))->(q0,(1,0,_,_),(R,R,R,S))");
    gotoxy(40,14);printf("&(q0,(1,1,_,_))->(q0,(1,1,_,_),(R,R,R,S))");
    gotoxy(40,15);printf("&(q1,(0,0,_,_))->(q1,(0,0,0,_),(L,L,L,S))");
    gotoxy(40,16);printf("&(q1,(0,1,_,_))->(q1,(0,1,1,_),(L,L,L,S))");
    gotoxy(40,17);printf("&(q1,(1,0,_,_))->(q1,(1,0,1,_),(L,L,L,S))");
    gotoxy(40,18);printf("&(q1,(1,1,_,_))->(q2,(1,1,0,1),(L,L,L,S))");
    gotoxy(40,19);printf("&(q2,(0,0,_,1))->(q1,(0,0,1,_),(L,L,L,S))");
    gotoxy(40,20);printf("&(q2,(0,1,_,1))->(q2,(0,1,0,1),(L,L,L,S))");
    gotoxy(40,21);printf("&(q2,(1,0,_,1))->(q2,(1,0,0,1),(L,L,L,S))");
    gotoxy(40,22);printf("&(q2,(1,1,_,1))->(q2,(1,1,1,1),(L,L,L,S))");
    gotoxy(40,23);printf("&(q0,(_,_,_,_))->(q1,(_,_,_,_),(L,L,L,S))");
    gotoxy(40,24);printf("&(q1,(_,_,_,_))->(q3,(_,_,_,_),(S,S,S,S))");
    gotoxy(40,25);printf("&(q2,(_,_,_,1))->(q3,(_,_,1,_),(S,S,L,S))");
    
    
    gotoxy(56,27);printf("Diagrama: ");
    gotoxy(42,28);system("pause"); 
}

void diagrama(){
	//lOS COMENTARIOS FUERON ESCRITOS POR EL AUTOR PARA MANTENER UN CONTROL. Heriberto Gomez Bolaina
	//Vestices
	circle(400,150,25); //q0
	circle(700,150,25); //q1
	circle(1000,150,25); //q3
	circle(850,450,25); //q5
	circle(850,450,20); //q5 Terminal

	ellipse(400, 130, 0, 180, 15, 30); //q0->q0
	ellipse(700, 130, 0, 180, 15, 30); //q1->q1
	ellipse(1000, 130, 0, 180, 15, 30); //q3->q3

	//Punta de Flecha de Ciclos
	line(380, 125, 385, 130);//flecha
	line(390,125,385,130); 	//..q0
	line(680, 125, 685, 130);//flecha
	line(690, 125, 685, 130);//..91
	line(980, 125, 985, 130);//flecha
	line(990,125,985,130); 	//..q3

	//Aristas
	line(425,150,675,150);//q0->q1
	line(722,140,977,140);//q1->q2
	line(977,160,722,160);//q2->q1
	line(700,175,835,428);//q1->q3
	line(1000,175,865,428);//q1->q3
	
	//Punta de Flecha de Aristas
	line(665,155,675,150);//flecha_q0->q1
	line(665,145,675,150);//flecha
	line(967,145,977,140);//flecha_q1->q2
	line(967,135,977,140);//flecha
	line(732,165,722,160);//flecha_q2->q1
	line(732,155,722,160);//flecha
	
	//Estados
	outtextxy(395, 145, "q0");
	outtextxy(695, 145, "q1");
	outtextxy(995, 145, "q2");
	outtextxy(845, 445, "q3");

	//Transiciones
	outtextxy(325,80,"0;0,R | 0;0;R | _;_;R | _;_;S"); //q0
	outtextxy(325,65,"0;0,R | 1;1;R | _;_;R | _;_;S"); //q0
	outtextxy(325,50,"1;1,R | 0;0;R | _;_;R | _;_;S"); //q0
	outtextxy(325,35,"1;1,R | 1;1;R | _;_;R | _;_;S"); //q0
	outtextxy(625,80,"0;0,L | 0;0;L | _;0;L | _;_;S"); //q1
	outtextxy(625,65,"0;0,L | 1;1;L | _;1;L | _;_;S"); //q1
	outtextxy(625,50,"1;1,L | 0;0;L | _;1;L | _;_;S"); //q1
	outtextxy(770,120,"1;1,L | 1;1;L | _;0;L | _;1;S"); //q1->q2
	outtextxy(770,170,"0;0,L | 0;0;L | _;1;L | 1;_;S"); //q2->q1
	outtextxy(925,80,"0;0,L | 1;1;L | _;0;L | 1;1;S"); //q2
	outtextxy(925,65,"1;1,L | 0;0;L | _;0;L | 1;1;S"); //q2
	outtextxy(925,50,"1;1,L | 1;1;L | _;1;L | 1;1;S"); //q2
	outtextxy(650,280,"_,_,S | _,_,S | _,_,S | _,_,S"); //q1->q3
	outtextxy(890,280,"_,_,S | _,_,S | _,1,L | 1,_,S"); //q2->q3
	outtextxy(470,130,"_,_,L | _,_,L | _,_,L | _,_,S"); //q0->q1
	//flecha de estado Inicial
	line(365,155,375,150);//flecha
	line(365,145,375,150);//flecha
	line(300,150,375,150);
	
}

void funcionamiento(){
	int cabezal=1,i,j,band;
    char cad_1[20], cad_2[20], cad_3[20], cad_4[20], aux_1[20],aux_2[20];
    do{
    	i=0;
    	
    	cad_3[1]='\0';
    	cad_4[1]='\0';
    	band=0;
    	gotoxy(71,4);printf("                             ");
    	gotoxy(45,4);printf("Ingrese la cadena 1: ");
    	gets(aux_1);
    	gotoxy(45,5);printf("Ingrese la cadena 2: ");
    	gets(aux_2);
    	cad_1[0]='x';
		cad_2[0]='x';
		for(i=0; aux_1[i]!='\0'; i++){
			cad_1[i+1]=aux_1[i];
			cad_2[i+1]=aux_2[i];
		}
		cad_1[i+1]='\0';
		cad_2[i+1]='\0';
		
		for(i=0; i<4; i++){
			arrg(12,1,1,7+(i*4));
			for(j=0; j<12; j++){
				gotoxy(3+(j*4),8+(i*4));printf("%c",254);//cinta
			}		
		}
		
		for(i=0; cad_1[i]!='\0'; i++){
			gotoxy(7+((i+1)*4),8);printf("%c",aux_1[i]);//cadena
		}
		j=2;
		for(i=0; cad_2[i]!='\0'; i++){
			gotoxy(7+((i+1)*4),8+4);printf("%c",aux_2[i]);//cadena
		}
		gotoxy(11,10); printf("%c",94);
		gotoxy(11,10+4); printf("%c",94);
		gotoxy(11,10+8); printf("%c",94);
		gotoxy(11,10+12); printf("%c",94);//cabezal
		gotoxy(10,4); printf("Estado Actual: ");
		band=estado_q0(cabezal,cad_1,cad_2,cad_3,cad_4);
	}while(band!=0);
}

int estado_q0(int cabezal,char cad_1[],char cad_2[],char cad_3[],char cad_4[]){
	int band,acarreo;
	do{
		gotoxy(25,4); printf("q0");
		Sleep(1500);
		band=0;
		if(cad_1[cabezal]=='0' && cad_2[cabezal] == '0' && cad_3[cabezal]=='\0' && cad_4[cabezal]=='\0'){
			cad_3[cabezal+1]='\0';
			cad_4[cabezal+1]='\0';
			
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			gotoxy(7+(cabezal*4),10+12); printf(" ");
			cabezal++;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			gotoxy(7+(cabezal*4),10+12); printf("%c",94);
			band=1;
			
		}
		else if(cad_1[cabezal]=='0' && cad_2[cabezal] == '1' && cad_3[cabezal]=='\0' && cad_4[cabezal]=='\0'){
			cad_3[cabezal+1]='\0';
			cad_4[cabezal+1]='\0';
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			gotoxy(7+(cabezal*4),10+12); printf(" ");
			cabezal++;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			gotoxy(7+(cabezal*4),10+12); printf("%c",94);
			band=1;
		}
		else if(cad_1[cabezal]=='1' && cad_2[cabezal] == '0' && cad_3[cabezal]=='\0' && cad_4[cabezal]=='\0'){
			cad_3[cabezal+1]='\0';
			cad_4[cabezal+1]='\0';
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			gotoxy(7+(cabezal*4),10+12); printf(" ");
			cabezal++;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			gotoxy(7+(cabezal*4),10+12); printf("%c",94);
			band=1;
		}
		else if(cad_1[cabezal]=='1' && cad_2[cabezal] == '1' && cad_3[cabezal]=='\0' && cad_4[cabezal]=='\0'){
			cad_3[cabezal+1]='\0';
			cad_4[cabezal+1]='\0';
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			gotoxy(7+(cabezal*4),10+12); printf(" ");
			cabezal++;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			gotoxy(7+(cabezal*4),10+12); printf("%c",94);
			band=1;
		}else if(cad_1[cabezal]=='\0' && cad_2[cabezal] == '\0' && cad_3[cabezal]=='\0' && cad_4[cabezal]=='\0'){
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			acarreo=cabezal;
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			estado_q1(cabezal,cad_1,cad_2,cad_3,cad_4,acarreo);
		}else{
			gotoxy(45,15); printf("La cadena no fue Aceptada\n");
		}
	}while(band!=0);
	return 0;
}


void estado_q1(int cabezal,char cad_1[],char cad_2[],char cad_3[],char cad_4[],int acarreo){
	int band;
	do{
		Sleep(1500);
		gotoxy(25,4); printf("q1");
		band=0;
		if(cad_1[cabezal]=='0' && cad_2[cabezal] == '0' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='\0'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='0';
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			band=1;
			
		}
		else if(cad_1[cabezal]=='0' && cad_2[cabezal] == '1' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='\0'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='1';
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			band=1;
		}
		else if(cad_1[cabezal]=='1' && cad_2[cabezal] == '0' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='\0'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='1';
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			band=1;
		}
		else if(cad_1[cabezal]=='1' && cad_2[cabezal] == '1' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='\0'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='0';
			cad_4[acarreo]='1';
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(acarreo*4),8+12);printf("%c",cad_4[acarreo]);
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			estado_q2(cabezal,cad_1,cad_2,cad_3,cad_4,acarreo);
		}else if(cad_1[cabezal]=='x' && cad_2[cabezal] == 'x' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='\0'){
			estado_q3(cabezal,cad_1,cad_2,cad_3,cad_4,acarreo);
		}else{
			gotoxy(45,15); printf("La cadena no fue Aceptada\n");
		}
	}while(band!=0);
}

void estado_q2(int cabezal,char cad_1[],char cad_2[],char cad_3[],char cad_4[],int acarreo){
	int band;
	do{
		gotoxy(25,4); printf("q2");
		Sleep(1500);
		band=0;
		if(cad_1[cabezal]=='0' && cad_2[cabezal] == '0' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='1'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='1';
			cad_4[acarreo]='\0';
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(acarreo*4),8+12);printf("%c",254);
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			estado_q1(cabezal,cad_1,cad_2,cad_3,cad_4,acarreo);
			band=1;
			
		}
		else if(cad_1[cabezal]=='0' && cad_2[cabezal] == '1' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='1'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='0';
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			band=1;
		}
		else if(cad_1[cabezal]=='1' && cad_2[cabezal] == '0' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='1'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='0';
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			band=1;
		}
		else if(cad_1[cabezal]=='1' && cad_2[cabezal] == '1' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='1'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='1';
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(cabezal*4),10); printf(" ");
			gotoxy(7+(cabezal*4),10+4); printf(" ");
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10); printf("%c",94);
			gotoxy(7+(cabezal*4),10+4); printf("%c",94);
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			estado_q2(cabezal,cad_1,cad_2,cad_3,cad_4,acarreo);
		}else if(cad_1[cabezal]=='x' && cad_2[cabezal] == 'x' && cad_3[cabezal]=='\0' && cad_4[acarreo]=='1'){
			cad_3[cabezal-1]='\0';
			cad_3[cabezal]='1';
			cad_4[acarreo]='\0';
			gotoxy(7+(acarreo*4),8+12);printf("%c",254);
			gotoxy(7+(cabezal*4),8+8);printf("%c",cad_3[cabezal]);
			gotoxy(7+(cabezal*4),10+8); printf(" ");
			cabezal--;
			gotoxy(7+(cabezal*4),10+8); printf("%c",94);
			estado_q3(cabezal,cad_1,cad_2,cad_3,cad_4,acarreo);
		}else{
			gotoxy(50,15); printf("La cadena no fue Aceptada\n");
		}
	}while(band!=0);
}

void estado_q3(int cabezal,char cad_1[],char cad_2[],char cad_3[],char cad_4[],int acarreo){
	gotoxy(25,4); printf("q3");
	gotoxy(50,15);printf("Suma lista");
}/*
void estado_q4(int cabezal,char numero[]){
	int band;
	setcolor(2);
	circle(1000,750,25); //q4
	gotoxy(25,4); printf("q4");
	do{
		band=0;
		Sleep(750);
		switch(numero[cabezal]){
			case '1':

				setcolor(2);
				outtextxy(980,680,"1;1,L"); //q4
				gotoxy(17+((cabezal)*4),10); printf(" ");
				cabezal--;
				gotoxy(17+(cabezal*4),10); printf("%c",94);
				band=1;
				break;
			case '0':

				setcolor(2);
				outtextxy(980,665,"0;0,L"); //q4
				gotoxy(17+((cabezal)*4),10); printf(" ");
				cabezal--;
				gotoxy(17+(cabezal*4),10); printf("%c",94);
				band=1;
				break;
			case 'a':

				setcolor(2);
				outtextxy(980,650,"a;a,L"); //q4
				gotoxy(17+((cabezal)*4),10); printf(" ");
				cabezal--;
				gotoxy(17+(cabezal*4),10); printf("%c",94);
				band=1;
				break;
			case 'b':

				setcolor(2);
				outtextxy(980,635,"b;b,L");
				gotoxy(17+((cabezal)*4),10); printf(" ");
				cabezal--;
				gotoxy(17+(cabezal*4),10); printf("%c",94);
				band=1;
				break;
			case 'x':

				setcolor(2);
				outtextxy(690, 595, "x;1,R");
				Sleep(200);
				setcolor(15);
				outtextxy(690, 595, "x;1,R");
				numero[cabezal]='1';
				gotoxy(17+(cabezal*4),8);printf("%c",numero[cabezal]);
				gotoxy(17+((cabezal)*4),10); printf(" ");
				cabezal++;
				gotoxy(17+(cabezal*4),10); printf("%c",94);
				setcolor(15);
				circle(1000,750,25); //q4
				estado_q0(cabezal,numero);
				break;
			default: 
				setcolor(4);
				circle(1000,750,25);
				Sleep(300);
				setcolor(15);
				circle(1000,750,25);
				gotoxy(45,15); printf("La cadena no fue Aceptada\n");
		}
		Sleep(200);
		setcolor(15);
		outtextxy(980,680,"1;1,L"); //q4
		setcolor(15);
		outtextxy(980,665,"0;0,L"); //q4
		setcolor(15);
		outtextxy(980,650,"a;a,L"); //q4
		setcolor(15);
		outtextxy(980,635,"b;b,L");
													
	}while(band!=0);
}
*/
