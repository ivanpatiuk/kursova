package lpnu.repository;

import lpnu.dto.AccountantDTO;
import lpnu.entity.Accountant;
import lpnu.entity.mapper.DTOConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

// Репозиторій класу бухгалтер
@Repository
public class AccountantRepository {
    private final Map<Long, Accountant> accountantRepository = new HashMap<>();
    private final DTOConvertor dtoConvertor;

    private static long accountantId = 0;

    @Autowired
    public AccountantRepository(DTOConvertor dtoConvertor) {
        this.dtoConvertor = dtoConvertor;
    }

    // Метод для додавання бухгалтера в базу даних
    public AccountantDTO addAccountant(final Accountant accountant){
        accountant.setId(++accountantId);
        accountantRepository.put(accountant.getId(), accountant);
        return dtoConvertor.convertToDto(accountant, AccountantDTO.class);
    }

    // Метод для отримання бухгалтера за ідентифікатором
    public Accountant getAccountantById(final Long id){
        return accountantRepository.getOrDefault(id, null);
    }
}
//************************************************
