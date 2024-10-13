@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public List<Meal> findAllMeals() {
        return mealRepository.findAll();
    }

    @Override
    public Meal findMealById(Integer id) {
        return mealRepository.findById(id).orElseThrow(() -> new RuntimeException("Meal not found"));
    }
}
