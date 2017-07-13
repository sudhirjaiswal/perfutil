
public class ChunkUpload {
	
	public int chunkcount(int filesize)
	{
		int chunk = 131072;  //128KB
		int chkcount = 0;
		int rem = 0;
		int quot = 0;
		if (filesize <= chunk){
			chkcount = 1;}
		else {
			rem = filesize % chunk;
			quot = filesize / chunk;
		if (rem > 0){
			chkcount = quot +1;
		} else
		{ chkcount = quot;
		}
		}
		return chkcount;
	}

	public static void main(String[] args)
	{
		
		ChunkUpload c1=new ChunkUpload();
		int result = c1.chunkcount(987654343);
		System.out.println("Chunks "+result);

	}

}
