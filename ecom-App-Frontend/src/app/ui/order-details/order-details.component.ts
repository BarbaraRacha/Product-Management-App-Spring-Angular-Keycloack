import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent implements OnInit{
  orderId : string;
  orderDetails : any;

  constructor(private route: ActivatedRoute, private http: HttpClient) {
    this.orderId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.http.get(`http://localhost:8088/api/orders/${this.orderId}`).subscribe({
      next: (order: any) => {
        this.orderDetails = order;
      },
      error: err => {
        console.error('Erreur lors de la récupération des détails de la commande :', err);
        console.log(err);
      }
    });
  }

  getTotal(orderDetails: any): number {
    let total : number = 0;
    if (orderDetails && orderDetails.productItems) {
      orderDetails.productItems.forEach((pi: any) => {
        total += pi.quantity * pi.price;
      });
    } else {
      console.error('orderDetails ou productItems est undefined');
    }

    return total;
  }
}
