BEGIN{
}
{
 if($6 == "cwnd_")
 {
 	if($2==0 && $4==5) printf("%4.2f\t%4.2f\t\n",$1,$7);
 	#if($2==02 && $4==6) printf("%4.2f\t%4.2f\t\n",$1,$7);
 }
}
END{
puts "DONE";
}
 	
