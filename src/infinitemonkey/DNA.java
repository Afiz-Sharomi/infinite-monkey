package infinitemonkey;

public class DNA {
	
	private String genes;
	private int fitness;
	private float normalisedFitness;
	
	public DNA(String genes)
	{
		this.genes = genes;
	}
	
	public void initFitness(String target)
	{
		int fitness = 0;
		for(int i=0; i<target.length(); i++)
		{
			if(this.genes.charAt(i) == target.charAt(i))
			{
				fitness++;
			}
		}
		this.fitness = fitness;
	}
	
	// Mating Functions
	public DNA mate(DNA otherDNA)
	{
		DNA newDNA = this.crossover(otherDNA);
		int shouldMutate = (int)(Math.random() * 100);
		if(shouldMutate < 5)
		{
			newDNA.mutate();
		}
		return newDNA;
	}
	
	private DNA crossover(DNA otherDNA)
	{
		int midPoint = (int)(Math.random()*this.genes.length());
		String genes = this.genes.substring(0,midPoint) + otherDNA.genes.substring(midPoint,this.genes.length());
		return new DNA(genes);
	}
	
	private void mutate()
	{
		int index = (int)(Math.random() * this.genes.length());
		char newGene = DNA_Pool.characters[(int) (Math.random() * 26)];
		this.genes = this.genes.substring(0, index) + newGene + this.genes.substring(index);
	}
	
	public String toString()
	{
		return String.format("DNA: %s; Fitness: %d; Normalised Fitness: %f.", this.genes, this.fitness, this.normalisedFitness);
	}
	
	// Getters and Setters
	public int getFitness()
	{
		return this.fitness;
	}
	public float getNormalisedFitness()
	{
		return this.normalisedFitness;
	}
	public void setNormalisedFitness(float normalisedFitness)
	{
		this.normalisedFitness = normalisedFitness;
	}
}
