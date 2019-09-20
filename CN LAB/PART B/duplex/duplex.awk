BEGIN{
	count=0;
}
{
	if($1=="d")
		count++;
}
END{ 	
	printf("NO of packets dropped is %d\n",count)
	
}
