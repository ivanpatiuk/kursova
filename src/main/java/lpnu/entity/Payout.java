package lpnu.entity;

import lpnu.enums.PayoutState;
import lpnu.enums.WithdrawType;

public class Payout {
    private PayoutState payoutState;
    private WithdrawType withdrawType;
    private Double sum;

    public Payout() {
    }

    public Payout(PayoutState payoutState, WithdrawType withdrawType, Double sum) {
        this.payoutState = payoutState;
        this.withdrawType = withdrawType;
        this.sum = sum;
    }

    public PayoutState getPayoutState() {
        return payoutState;
    }

    public void setPayoutState(PayoutState payoutState) {
        this.payoutState = payoutState;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public WithdrawType getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(WithdrawType withdrawType) {
        this.withdrawType = withdrawType;
    }
}
