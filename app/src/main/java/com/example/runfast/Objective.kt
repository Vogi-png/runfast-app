class Objective : Fragment(R.layout.fragment_objective) {

    private var _binding: FragmentObjectiveBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentObjectiveBinding.bind(view)

        // 1. Criamos a lista de fragmentos que vão deslizar
        val fragments = listOf(Goal(), Inspiration())

        // 2. Configuramos o Adapter (o motor que faz o slide)
        val adapter = object : androidx.viewpager2.adapter.FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragments.size
            override fun createFragment(position: Int): Fragment = fragments[position]
        }

        binding.viewPagerAbas.adapter = adapter

        // 3. Conectamos o TabLayout com o ViewPager2
        com.google.android.material.tabs.TabLayoutMediator(
            binding.navigationTabLayout2,
            binding.viewPagerAbas
        ) { tab, position ->
            tab.text = if (position == 0) "Meta" else "Inspiração"
        }.attach()

        // O código do StackOverflow de "transparência" (se quiser aplicar no slide)
        binding.viewPagerAbas.setPageTransformer { page, position ->
            page.alpha = 1 - Math.abs(position) // Efeito de fade durante o slide
        }
    }
}