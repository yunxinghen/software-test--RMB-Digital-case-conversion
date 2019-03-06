#include<iostream>
#include<string>
#include<algorithm>
using namespace std;
int bits[20];
string numConvert[10]={"��","Ҽ","��","��","��","��","½","��","��","��"};
bool IsRemainAllZero(int begin,int end)
{
 for(int i=begin;i>=end;i--)
 {
  if(bits[i]!=0)
   return false;
 }
 return true;
}
void ConvertInteger(string moneyStr)
{
 cout<<"�����";
 for(int i=moneyStr.length()-1;i>=0;i--)
 {
  if(i+1<moneyStr.length()&&(i+1)%4==0&&bits[i+1]==0&&(i+1)!=0&&bits[i]!=0) 
   printf("��");
  if(bits[i]==0)
  {
   if(bits[i+1]!=0&&(i+1)%4!=1)
    cout<<numConvert[bits[i]];
   goto DES1;
  }
  else
  {
   cout<<numConvert[bits[i]];
  }
  switch((i+1)%4)
  {
   case 2:
   {
    cout<<"ʰ";
    break;
   }
   case 3:
   {
    cout<<"��";
    break;
   }
   case 0:
   {
    cout<<"Ǫ";
    break;
   } 
  }
DES1:
  switch(i+1)
  {
   case 9:
    {
     cout<<"��";
     break; 
    }
   case 5:
    {
     cout<<"��";
     break; 
    } 
  }
  if(IsRemainAllZero(i-1,0))
   break;
 }
 cout<<"Ԫ"; 
}
void ConvertDecimal(string decimalStr)
{
 for(int i=0;i<decimalStr.length();i++)
 {
  if(i==0)
  {
   cout<<numConvert[decimalStr[i]-'0'];
   if(decimalStr[i]-'0'!=0)
    cout<<"��";
  }
  else if(i==1)
  {
   if(decimalStr[i]-'0'!=0)
   {
    cout<<numConvert[decimalStr[i]-'0'];
    cout<<"��";
   }
  }
 }
 cout<<endl;
}
void SplitByDot(string str,string &str1,string &str2)
{
 int i;
 for(i=0;i<str.length();i++)
 {
  if(str[i]=='.')
   break;
 }
 if(i>0&&i<str.length()-1)
 {
  str1=str.substr(0,i);
  str2=str.substr(i+1,str.length()-1-i);
 }
 else
 {
  str1=str;
  str2="";
 }
}
int main()
{
 while(1)
 {
  string moneyStr;
  string integerStr,decimalStr;
  cin>>moneyStr;
  SplitByDot(moneyStr,integerStr,decimalStr);
  reverse(integerStr.begin(),integerStr.end());
  for(int i=0;i<integerStr.length();i++)
  {
   bits[i]=integerStr[i]-'0';
  }
  if(integerStr=="0")
   cout<<"�������Ԫ";
  else
   ConvertInteger(integerStr);
  if(decimalStr==""||decimalStr=="0"||decimalStr=="00")
  {
   cout<<"��"<<endl;
   continue;
  }
  else
  {
   ConvertDecimal(decimalStr);
  }
 }
 return 0;
}
