import android.app.AlertDialog
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.somativaandroid.EditUserActivity
import com.example.somativaandroid.MainActivity
import com.example.somativaandroid.R
import com.example.somativaandroid.databinding.ProfileFragmentBinding
import com.example.somativaandroid.recyclerviewpackage.UserDAO
import com.example.somativaandroid.recyclerviewpackage.userDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    private lateinit var userDao: UserDAO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val loggedInUserEmail = sharedPreferences.getString("loggedInUserEmail", "teste")
        val loggedInUsername = sharedPreferences.getString("loggedInUser", "teste")
        userDao = userDatabase.getInstance(requireContext())?.UserDAO()!!

        binding.userName.text = loggedInUsername
        binding.galacticEmailAdress.text = loggedInUserEmail

        binding.editProfileButton.setOnClickListener {
            // Intent para navegar para a EditUserActivity
            val intent = Intent(requireActivity(), EditUserActivity::class.java)
            startActivity(intent)
        }

        binding.deleteProfileButton.setOnClickListener {
            // Exibir o diálogo de confirmação
            showDeleteConfirmationDialog()
        }
    }

    override fun onResume() {
        super.onResume()

        // Recarregar os dados do SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val loggedInUserEmail = sharedPreferences.getString("loggedInUserEmail", "")
        val loggedInUsername = sharedPreferences.getString("loggedInUser", "")

        // Atualize os elementos da interface
        binding.userName.text = loggedInUsername
        binding.galacticEmailAdress.text = loggedInUserEmail
    }

    // Método para mostrar o diálogo de confirmação
    private fun showDeleteConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Confirm Deletion")
            .setMessage("Are you sure you want to delete your profile? This action cannot be undone.")
            .setPositiveButton("Yes") { _, _ ->
                deleteUserProfile()
            }
            .setNegativeButton("No", null)
            .create()

        alertDialog.show()
    }

    // Método para deletar o perfil
    private fun deleteUserProfile() {
        val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", MODE_PRIVATE)

        // Simulação da exclusão do perfil do banco de dados e SharedPreferences
        lifecycleScope.launch(Dispatchers.IO) {
            // Aqui você pode colocar a lógica para excluir o perfil do banco de dados (se aplicável)
            val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", MODE_PRIVATE)
            val loggedInUserEmail = sharedPreferences.getString("loggedInUserEmail", "teste")
            val user = userDao.getUserByEmail(loggedInUserEmail ?: "")

            if (user != null) {
                userDao.delete(user)
            }
            // Limpar SharedPreferences
            with(sharedPreferences.edit()) {
                clear() // Remove todos os dados do SharedPreferences
                apply()
            }

            withContext(Dispatchers.Main) {
                // Notificar o usuário de que o perfil foi excluído
                Toast.makeText(requireContext(), "Profile deleted successfully!", Toast.LENGTH_SHORT).show()

                // Redirecionar para a tela de login ou inicial
                val intent = Intent(requireActivity(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }
}
